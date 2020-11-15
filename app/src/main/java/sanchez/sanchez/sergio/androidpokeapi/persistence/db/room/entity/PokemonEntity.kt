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
    // Pokemon Image URL
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    // Pokemon abilities
    @ColumnInfo(name = "abilities")
    val abilities: List<PokemonAbilityEntity>,
    // Pokemon Moves
    @ColumnInfo(name = "moves")
    val moves: List<PokemonMoveEntity>,
    // Pokemon Types
    @ColumnInfo(name = "types")
    val types: List<PokemonTypeEntity>,
    // Pokemon Sprites
    @ColumnInfo(name = "sprites")
    val sprites: PokemonSpriteEntity,
    // Pokemon Stats
    @ColumnInfo(name = "stats")
    val stats: List<PokemonStatEntity>
)
