package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.core

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonDetailNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.PokemonNetworkRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.IPokemonNetworkRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.service.IPokemonService

/**
 * Pokemon Network Module
 */
@Module
class PokemonNetworkModule {

    /**
     * Provide Pokemon Network Service
     * @param retrofit
     */
    @Provides
    @PerFragment
    fun providePokemonNetworkService(retrofit: Retrofit): IPokemonService =
        retrofit.create(IPokemonService::class.java)

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
     * @param pokemonNetworkService
     * @param pokemonNetworkMapper
     *
     */
    @Provides
    @PerFragment
    fun provideCharacterNetworkRepository(
        pokemonNetworkService: IPokemonService,
        pokemonNetworkMapper: PokemonNetworkMapper,
        pokemonDetailNetworkMapper: PokemonDetailNetworkMapper
    ): IPokemonNetworkRepository =
        PokemonNetworkRepositoryImpl(pokemonNetworkService, pokemonNetworkMapper, pokemonDetailNetworkMapper)

}