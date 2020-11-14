package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ViewModelModule
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.viewmodel.ViewModelKey
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.features.list.PokemonListViewModel

/**
 * Pokemon List UI Module
 */
@Module(includes = [ ViewModelModule::class ])
abstract class PokemonListUIModule {

    @PerFragment
    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    abstract fun bindsPokemonListViewModel(pokemonListViewModel: PokemonListViewModel): ViewModel
}