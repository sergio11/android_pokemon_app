package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.splash

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ViewModelModule
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.viewmodel.ViewModelKey
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.features.splash.SplashViewModel

/**
 * Pokemon Splash UI Module
 */
@Module(includes = [ ViewModelModule::class ])
abstract class PokemonSplashUIModule {

    @PerFragment
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindsSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}