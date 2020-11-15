package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity.*
import java.util.*


/**
 * All Converters for save entities
 */
class Converters {

    /**
     * From Timestamp
     * @param value
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    /**
     * Date to Timestamp
     * @param date
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun pokemonAbilityToJson(value: List<PokemonAbilityEntity>): String =
            toJson(value)

    @TypeConverter
    fun jsonToPokemonAbility(value: String): List<PokemonAbilityEntity>? =
            fromJson<List<PokemonAbilityEntity>>(value)

    @TypeConverter
    fun pokemonMoveToJson(value: List<PokemonMoveEntity>): String =
            toJson(value)

    @TypeConverter
    fun jsonToPokemonMove(value: String): List<PokemonMoveEntity>? =
            fromJson<List<PokemonMoveEntity>>(value)

    @TypeConverter
    fun pokemonTypeToJson(value: List<PokemonTypeEntity>): String =
            toJson(value)

    @TypeConverter
    fun jsonToPokemonType(value: String): List<PokemonTypeEntity>? =
            fromJson<List<PokemonTypeEntity>>(value)

    @TypeConverter
    fun pokemonSpriteEntityToJson(value: PokemonSpriteEntity): String =
            toJson(value)

    @TypeConverter
    fun jsonToPokemonSpriteEntity(value: String): PokemonSpriteEntity? =
            fromJson<PokemonSpriteEntity>(value)

    @TypeConverter
    fun pokemonStatEntityToJson(value: List<PokemonStatEntity>): String =
        toJson(value)

    @TypeConverter
    fun jsonToPokemonStatEntity(value: String): List<PokemonStatEntity>? =
        fromJson<List<PokemonStatEntity>>(value)
    /**
     * Private Methods
     */
    private inline fun <reified T> toJson(model: T): String =
        Gson().getAdapter(T::class.java).toJson(model)

    private inline fun <reified T> fromJson(value: String): T? =
        Gson().getAdapter(T::class.java).fromJson(value)
}