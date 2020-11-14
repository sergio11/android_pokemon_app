package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.converters.Converters
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.pokemon.PokemonDAOImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppRoomDatabase : RoomDatabase() {

    /**
     * DAOs declarations
     */
    abstract fun pokemonDAO(): PokemonDAOImpl

    companion object {

        const val DATABASE_NAME = "APP_DATABASE"
    }
}