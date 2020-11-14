package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity

import androidx.room.ColumnInfo

data class PokemonMoveEntity (
        @ColumnInfo(name = "name")
        val name: String
)