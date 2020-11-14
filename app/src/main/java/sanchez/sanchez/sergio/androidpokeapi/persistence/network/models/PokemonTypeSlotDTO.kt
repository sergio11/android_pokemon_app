package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json


data class PokemonTypeSlotDTO (
        @field:Json(name = "slot")
        val slot: Int,
        @field:Json(name = "type")
        val type: PokemonTypeDTO
)