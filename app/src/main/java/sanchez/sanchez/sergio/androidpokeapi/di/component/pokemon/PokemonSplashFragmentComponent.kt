package sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon

import dagger.Subcomponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.core.FragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.splash.PokemonSplashUIModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.features.splash.SplashFragment

@PerFragment
@Subcomponent(modules = [ PokemonSplashUIModule::class])
interface PokemonSplashFragmentComponent: FragmentComponent {

    /**
     * Inject into Pokemon Splash Fragment
     */
    fun inject(pokemonSplashFragment: SplashFragment)
}