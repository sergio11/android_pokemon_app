package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity

import androidx.room.ColumnInfo

data class PokemonTypeEntity (
        @ColumnInfo(name = "slot")
        val slot: Int,
        @ColumnInfo(name = "name")
        val name: String
)