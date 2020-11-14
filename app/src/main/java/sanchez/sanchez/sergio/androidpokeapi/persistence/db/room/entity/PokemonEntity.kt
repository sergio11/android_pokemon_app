package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Pokemon Entity Model
 */

@Entity(tableName = "pokemons")
data class PokemonEntity (
    // Pokemon Id
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    // Pokemon Name
    @NonNull
    @ColumnInfo(name = "name")
    val name: String,
    // Pokemon Height
    @ColumnInfo(name = "height")
    val height: Int,
    // Pokemon weight
    @ColumnInfo(name = "weight")
    @NonNull
    val weight: Int,
)
