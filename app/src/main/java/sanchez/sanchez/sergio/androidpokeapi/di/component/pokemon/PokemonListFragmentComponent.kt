package sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon

import dagger.Subcomponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.core.FragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.list.PokemonListDomainModule
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.list.PokemonListUIModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.features.list.PokemonListFragment

@PerFragment
@Subcomponent(modules = [ PokemonListUIModule::class, PokemonListDomainModule::class ])
interface PokemonListFragmentComponent: FragmentComponent {

    /**
     * Inject into Pokemon List Fragment
     */
    fun inject(pokemonListFragment: PokemonListFragment)
}