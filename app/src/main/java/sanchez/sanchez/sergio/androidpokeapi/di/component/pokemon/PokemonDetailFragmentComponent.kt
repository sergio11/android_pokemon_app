package sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon

import dagger.Subcomponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.core.FragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.detail.PokemonDetailDomainModule
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.detail.PokemonDetailUIModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.features.detail.PokemonDetailFragment

@PerFragment
@Subcomponent(modules = [ PokemonDetailUIModule::class, PokemonDetailDomainModule::class ])
interface PokemonDetailFragmentComponent: FragmentComponent {

    /**
     * Inject into Pokemon Detail Fragment
     */
    fun inject(pokemonDetailFragment: PokemonDetailFragment)
}