package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity

import androidx.room.ColumnInfo

data class PokemonAbilityEntity (
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "is_hidden")
        val isHidden: Boolean,
        @ColumnInfo(name = "slot")
        val slot: Int
)