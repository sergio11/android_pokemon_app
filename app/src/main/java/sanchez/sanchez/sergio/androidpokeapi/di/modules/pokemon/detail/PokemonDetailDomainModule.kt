package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.detail

import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.core.PokemonRepositoryModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.domain.interact.FindPokemonDetailByNameInteract
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.IPokemonRepository

/**
 * Pokemon Detail Domain Module
 */
@Module(includes = [PokemonRepositoryModule::class])
class PokemonDetailDomainModule {

    /**
     * @param pokemonRepository
     */
    @Provides
    @PerFragment
    fun provideFindPokemonByNameInteract(
        pokemonRepository: IPokemonRepository
    ): FindPokemonDetailByNameInteract = FindPokemonDetailByNameInteract(pokemonRepository)

}