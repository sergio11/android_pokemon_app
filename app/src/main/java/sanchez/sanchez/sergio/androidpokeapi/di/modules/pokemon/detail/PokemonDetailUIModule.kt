package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ViewModelModule
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.viewmodel.ViewModelKey
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.features.detail.PokemonDetailViewModel

/**
 * Pokemon Detail UI Module
 */
@Module(includes = [ ViewModelModule::class ])
abstract class PokemonDetailUIModule {

    @PerFragment
    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    abstract fun bindsPokemonDetailViewModel(pokemonDetailViewModel: PokemonDetailViewModel): ViewModel
}