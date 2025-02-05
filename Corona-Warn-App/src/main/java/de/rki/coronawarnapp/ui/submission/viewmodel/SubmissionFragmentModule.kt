package de.rki.coronawarnapp.ui.submission.viewmodel

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rki.coronawarnapp.submission.ui.testresults.negative.RATResultNegativeFragment
import de.rki.coronawarnapp.submission.ui.testresults.negative.RATResultNegativeModule
import de.rki.coronawarnapp.ui.submission.fragment.SubmissionContactFragment
import de.rki.coronawarnapp.ui.submission.fragment.SubmissionDispatcherFragment
import de.rki.coronawarnapp.ui.submission.deletionwarning.SubmissionDeletionWarningFragment
import de.rki.coronawarnapp.ui.submission.deletionwarning.SubmissionDeletionWarningModule
import de.rki.coronawarnapp.ui.submission.covidcertificate.RequestCovidCertificateFragment
import de.rki.coronawarnapp.ui.submission.covidcertificate.RequestCovidCertificateFragmentModule
import de.rki.coronawarnapp.ui.submission.qrcode.consent.SubmissionConsentFragment
import de.rki.coronawarnapp.ui.submission.qrcode.consent.SubmissionConsentModule
import de.rki.coronawarnapp.ui.submission.resultavailable.SubmissionTestResultAvailableFragment
import de.rki.coronawarnapp.ui.submission.resultavailable.SubmissionTestResultAvailableModule
import de.rki.coronawarnapp.ui.submission.resultready.SubmissionResultReadyFragment
import de.rki.coronawarnapp.ui.submission.resultready.SubmissionResultReadyModule
import de.rki.coronawarnapp.ui.submission.submissiondone.SubmissionDoneFragment
import de.rki.coronawarnapp.ui.submission.submissiondone.SubmissionDoneModule
import de.rki.coronawarnapp.ui.submission.symptoms.calendar.SubmissionSymptomCalendarFragment
import de.rki.coronawarnapp.ui.submission.symptoms.calendar.SubmissionSymptomCalendarModule
import de.rki.coronawarnapp.ui.submission.symptoms.introduction.SubmissionSymptomIntroductionFragment
import de.rki.coronawarnapp.ui.submission.symptoms.introduction.SubmissionSymptomIntroductionModule
import de.rki.coronawarnapp.ui.submission.tan.SubmissionTanFragment
import de.rki.coronawarnapp.ui.submission.tan.SubmissionTanModule
import de.rki.coronawarnapp.ui.submission.testresult.invalid.SubmissionTestResultInvalidFragment
import de.rki.coronawarnapp.ui.submission.testresult.invalid.SubmissionTestResultInvalidModule
import de.rki.coronawarnapp.ui.submission.testresult.negative.SubmissionTestResultNegativeFragment
import de.rki.coronawarnapp.ui.submission.testresult.negative.SubmissionTestResultNegativeModule
import de.rki.coronawarnapp.ui.submission.testresult.pending.SubmissionTestResultPendingFragment
import de.rki.coronawarnapp.ui.submission.testresult.pending.SubmissionTestResultPendingModule
import de.rki.coronawarnapp.ui.submission.testresult.positive.SubmissionTestResultConsentGivenFragment
import de.rki.coronawarnapp.ui.submission.testresult.positive.SubmissionTestResultConsentGivenModule
import de.rki.coronawarnapp.ui.submission.testresult.positive.SubmissionTestResultKeysSharedFragment
import de.rki.coronawarnapp.ui.submission.testresult.positive.SubmissionTestResultKeysSharedModule
import de.rki.coronawarnapp.ui.submission.testresult.positive.SubmissionTestResultNoConsentFragment
import de.rki.coronawarnapp.ui.submission.testresult.positive.SubmissionTestResultNoConsentModule
import de.rki.coronawarnapp.ui.submission.warnothers.SubmissionResultPositiveOtherWarningNoConsentFragment
import de.rki.coronawarnapp.ui.submission.warnothers.SubmissionResultPositiveOtherWarningNoConsentModule
import de.rki.coronawarnapp.ui.submission.yourconsent.SubmissionYourConsentFragment
import de.rki.coronawarnapp.ui.submission.yourconsent.SubmissionYourConsentModule

@Suppress("FunctionNaming", "MaxLineLength", "TooManyFunctions")
@Module
internal abstract class SubmissionFragmentModule {

    // activity specific injection module for future dependencies

    // example:
    // @ContributesAndroidInjector
    // abstract fun onboardingFragment(): OnboardingFragmentFolder

    @ContributesAndroidInjector(modules = [SubmissionTanModule::class])
    abstract fun submissionTanScreen(): SubmissionTanFragment

    @ContributesAndroidInjector(modules = [SubmissionDispatcherModule::class])
    abstract fun submissionDispatcherScreen(): SubmissionDispatcherFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultPendingModule::class])
    abstract fun submissionTestResultPendingScreen(): SubmissionTestResultPendingFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultNegativeModule::class])
    abstract fun submissionTestResultNegativeScreen(): SubmissionTestResultNegativeFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultInvalidModule::class])
    abstract fun submissionTestResultInvalidScreen(): SubmissionTestResultInvalidFragment

    @ContributesAndroidInjector(modules = [SubmissionContactModule::class])
    abstract fun submissionContactScreen(): SubmissionContactFragment

    @ContributesAndroidInjector(modules = [SubmissionResultReadyModule::class])
    abstract fun submissionDoneNoConsentScreen(): SubmissionResultReadyFragment

    @ContributesAndroidInjector(modules = [SubmissionSymptomIntroductionModule::class])
    abstract fun submissionSymptomIntroductionScreen(): SubmissionSymptomIntroductionFragment

    @ContributesAndroidInjector(modules = [SubmissionSymptomCalendarModule::class])
    abstract fun submissionSymptomCalendarScreen(): SubmissionSymptomCalendarFragment

    @ContributesAndroidInjector(modules = [SubmissionConsentModule::class])
    abstract fun submissionConsentScreen(): SubmissionConsentFragment

    @ContributesAndroidInjector(modules = [SubmissionYourConsentModule::class])
    abstract fun submissionYourConsentScreen(): SubmissionYourConsentFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultAvailableModule::class])
    abstract fun submissionTestResultAvailableScreen(): SubmissionTestResultAvailableFragment

    @ContributesAndroidInjector(modules = [SubmissionDoneModule::class])
    abstract fun submissionDoneScreen(): SubmissionDoneFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultConsentGivenModule::class])
    abstract fun submissionTestResultConsentGivenScreen(): SubmissionTestResultConsentGivenFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultNoConsentModule::class])
    abstract fun submissionTestResultNoConsentScreen(): SubmissionTestResultNoConsentFragment

    @ContributesAndroidInjector(modules = [SubmissionResultPositiveOtherWarningNoConsentModule::class])
    abstract fun submissionResultPositiveOtherWarningNoConsentScreen(): SubmissionResultPositiveOtherWarningNoConsentFragment

    @ContributesAndroidInjector(modules = [SubmissionDeletionWarningModule::class])
    abstract fun submissionDeletionWarningScreen(): SubmissionDeletionWarningFragment

    @ContributesAndroidInjector(modules = [RATResultNegativeModule::class])
    abstract fun submissionNegativeRATResultScreen(): RATResultNegativeFragment

    @ContributesAndroidInjector(modules = [SubmissionTestResultKeysSharedModule::class])
    abstract fun submissionTestResultKeysSharedScreen(): SubmissionTestResultKeysSharedFragment

    @ContributesAndroidInjector(modules = [RequestCovidCertificateFragmentModule::class])
    abstract fun requestCovidCertificateFragment(): RequestCovidCertificateFragment
}
