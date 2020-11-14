package sanchez.sanchez.sergio.androidpokeapi.di.modules.pokemon.core

import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon.PokemonDBRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon.IPokemonDBRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.AppRoomDatabase
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.pokemon.IPokemonDAO
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.mapper.PokemonDBMapper

/**
 * Pokemon DB Module
 */
@Module
class PokemonDBModule {

    /**
     * Provide Pokemon DB Mapper
     */
    @Provides
    @PerFragment
    fun providePokemonDBMapper(): PokemonDBMapper =
        PokemonDBMapper()

    /**
     * Provide Pokemon DAO
     * @param database
     */
    @Provides
    @PerFragment
    fun providePokemonDao(database: AppRoomDatabase): IPokemonDAO =
        database.pokemonDAO()

    /**
     * Provide Pokemon DB Repository
     * @param pokemonDBMapper
     * @param pokemonDao
     */
    @Provides
    @PerFragment
    fun providePokemonDBRepository(pokemonDBMapper: PokemonDBMapper, pokemonDao: IPokemonDAO): IPokemonDBRepository =
        PokemonDBRepositoryImpl(pokemonDao, pokemonDBMapper)
}