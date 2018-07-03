package fi.exa.cthulhuhelpper.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.exa.cthulhuhelpper.fragment.GameActivityFragment
import fi.exa.cthulhuhelpper.fragment.TokenConfigFragment

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeGameFragment(): GameActivityFragment

    @ContributesAndroidInjector
    abstract fun contributeTokenConfigFragment(): TokenConfigFragment
}