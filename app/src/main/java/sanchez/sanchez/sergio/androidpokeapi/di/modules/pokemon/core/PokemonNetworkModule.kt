package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.core

import dagger.Module
import dagger.Provides
import io.ktor.client.*
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonDetailNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.IPokemonNetworkRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.PokemonNetworkRepositoryImpl


/**
 * Pokemon Network Module
 */
@Module
class PokemonNetworkModule {

    /**
     * Private Pokemon Network Mapper
     */
    @Provides
    @PerFragment
    fun privatePokemonNetworkMapper(): PokemonNetworkMapper = PokemonNetworkMapper()

    /**
     * Private Pokemon Detail Network Mapper
     */
    @Provides
    @PerFragment
    fun privatePokemonDetailNetworkMapper(): PokemonDetailNetworkMapper = PokemonDetailNetworkMapper()

    /**
     * Provide Pokemon Network Repository
     * @param httpClient
     * @param pokemonNetworkMapper
     * @param pokemonDetailNetworkMapper
     */
    @Provides
    @PerFragment
    fun provideCharacterNetworkRepository(
        httpClient: HttpClient,
        pokemonNetworkMapper: PokemonNetworkMapper,
        pokemonDetailNetworkMapper: PokemonDetailNetworkMapper
    ): IPokemonNetworkRepository =
        PokemonNetworkRepositoryImpl(httpClient, pokemonNetworkMapper, pokemonDetailNetworkMapper)

}