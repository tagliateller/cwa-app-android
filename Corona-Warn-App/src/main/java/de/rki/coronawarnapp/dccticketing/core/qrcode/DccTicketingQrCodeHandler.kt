package de.rki.coronawarnapp.dccticketing.core.qrcode

import de.rki.coronawarnapp.dccticketing.core.allowlist.data.DccTicketingValidationServiceAllowListEntry
import de.rki.coronawarnapp.dccticketing.core.allowlist.internal.DccTicketingAllowListException
import de.rki.coronawarnapp.dccticketing.core.allowlist.internal.DccTicketingAllowListException.ErrorCode
import de.rki.coronawarnapp.dccticketing.core.allowlist.data.DccTicketingServiceProviderAllowListEntry
import de.rki.coronawarnapp.dccticketing.core.allowlist.filtering.DccTicketingJwkFilter
import de.rki.coronawarnapp.dccticketing.core.allowlist.repo.DccTicketingAllowListRepository
import de.rki.coronawarnapp.dccticketing.core.common.DccTicketingException
import de.rki.coronawarnapp.dccticketing.core.common.DccTicketingException.ErrorCode.SP_ALLOWLIST_NO_MATCH
import de.rki.coronawarnapp.dccticketing.core.service.DccTicketingRequestService
import de.rki.coronawarnapp.dccticketing.core.transaction.DccTicketingTransactionContext
import okio.ByteString.Companion.encode
import timber.log.Timber
import javax.inject.Inject

class DccTicketingQrCodeHandler @Inject constructor(
    private val requestService: DccTicketingRequestService,
    private val dccTicketingJwkFilter: DccTicketingJwkFilter,
    private val allowListRepository: DccTicketingAllowListRepository,
    private val qrCodeSettings: DccTicketingQrCodeSettings,
) {
    suspend fun handleQrCode(qrCode: DccTicketingQrCode): DccTicketingTransactionContext {
        val container = allowListRepository.refresh()

        container.serviceProviderAllowList.validateServiceIdentity(
            qrCode.data.serviceIdentity
        )

        val validationServiceAllowList = container.validationServiceAllowList

        val transactionContext = DccTicketingTransactionContext(
            initializationData = qrCode.data
        ).decorate(validationServiceAllowList)

        val filteringResult = dccTicketingJwkFilter.filter(
            transactionContext.validationServiceJwkSet.orEmpty(),
            validationServiceAllowList
        )
        if (filteringResult.filteredJwkSet.isEmpty()) {
            throw DccTicketingAllowListException(ErrorCode.ALLOWLIST_NO_MATCH)
        }

        return transactionContext.copy(
            allowlist = filteringResult.filteredAllowlist,
            validationServiceJwkSet = filteringResult.filteredJwkSet
        )
    }

    private fun Set<DccTicketingServiceProviderAllowListEntry>.validateServiceIdentity(serviceIdentity: String) {
        if (!qrCodeSettings.checkServiceIdentity.value) {
            Timber.i("Service identity check is turned off.")
            return
        }
        Timber.v("Service identity check is turned on.")
        Timber.v("Allowed hashes are $this.")

        val hash = serviceIdentity.toHash().also {
            Timber.v("Calculated hash of service identity is $it")
        }
        find { it.serviceIdentityHash == hash } ?: throw DccTicketingException(SP_ALLOWLIST_NO_MATCH).also {
            Timber.e("Service identity check failed.")
        }

        Timber.i("Service identity check passed.")
    }

    suspend fun DccTicketingTransactionContext.decorate(
        validationServiceAllowList: Set<DccTicketingValidationServiceAllowListEntry>
    ): DccTicketingTransactionContext {
        val decorator =
            requestService.requestValidationDecorator(
                initializationData.serviceIdentity,
                validationServiceAllowList
            )
        return copy(
            accessTokenService = decorator.accessTokenService,
            accessTokenServiceJwkSet = decorator.accessTokenServiceJwkSet,
            accessTokenSignJwkSet = decorator.accessTokenSignJwkSet,
            validationService = decorator.validationService,
            validationServiceJwkSet = decorator.validationServiceJwkSet,
        )
    }
}

internal fun String.toHash() = encode().sha256()
