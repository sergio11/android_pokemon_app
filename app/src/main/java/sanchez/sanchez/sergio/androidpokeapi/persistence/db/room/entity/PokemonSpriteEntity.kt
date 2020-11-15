package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity

import androidx.room.ColumnInfo

data class PokemonSpriteEntity (
        @ColumnInfo(name = "back_default")
        val backDefault : String?,
        @ColumnInfo(name = "back_shiny")
        val backShiny : String?,
        @ColumnInfo(name = "front_default")
        val frontDefault : String?,
        @ColumnInfo(name = "front_shiny")
        val frontShiny : String?
)