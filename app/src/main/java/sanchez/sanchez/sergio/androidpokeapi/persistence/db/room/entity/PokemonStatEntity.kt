package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity

import androidx.room.ColumnInfo

data class PokemonStatEntity (
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "base_stat")
        val baseStat: Int,
        @ColumnInfo(name = "effort")
        val effort: Int
)