package fi.exa.cthulhuhelpper.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import fi.exa.cthulhuhelpper.viewmodel.CthulhuViewModelFactory
import fi.exa.cthulhuhelpper.viewmodel.TokenViewModel
import kotlin.reflect.KClass

@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Suppress("UNUSED")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TokenViewModel::class)
    abstract fun bindsTokenViewModel(tokenViewModel: TokenViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(cthulhuViewModelFactory: CthulhuViewModelFactory):
            ViewModelProvider.Factory
}