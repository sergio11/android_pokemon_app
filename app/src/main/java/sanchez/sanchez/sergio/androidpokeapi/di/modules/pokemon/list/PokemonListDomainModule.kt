package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.list

import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.core.PokemonRepositoryModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.domain.interact.FindPaginatedPokemonListInteract
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.IPokemonRepository

/**
 * Pokemon List Domain Module
 */
@Module(includes = [PokemonRepositoryModule::class])
class PokemonListDomainModule {

    @Provides
    @PerFragment
    fun provideFindPaginatedPokemonListInteract(
        pokemonRepository: IPokemonRepository
    ): FindPaginatedPokemonListInteract = FindPaginatedPokemonListInteract(pokemonRepository)

}