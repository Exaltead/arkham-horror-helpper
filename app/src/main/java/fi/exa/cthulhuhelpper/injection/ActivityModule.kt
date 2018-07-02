package fi.exa.cthulhuhelpper.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.exa.cthulhuhelpper.GameActivity

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeGameActivity(): GameActivity
}