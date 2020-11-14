package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.core

import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.PokemonRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.IPokemonRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon.IPokemonDBRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.IPokemonNetworkRepository

/**
 * Pokemon Repository Module
 */
@Module(includes = [PokemonDBModule::class, PokemonNetworkModule::class])
class PokemonRepositoryModule {

    /**
     * Provide Pokemon Repository
     * @param pokemonNetworkRepository
     * @param pokemonDBRepository
     */
    @Provides
    @PerFragment
    fun providePokemonRepository(
        pokemonNetworkRepository: IPokemonNetworkRepository,
        pokemonDBRepository: IPokemonDBRepository
    ): IPokemonRepository =
        PokemonRepositoryImpl(pokemonNetworkRepository, pokemonDBRepository)
}