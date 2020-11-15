package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName


data class PokemonTypeSlotDTO (
        @SerializedName(value = "slot")
        val slot: Int,
        @SerializedName(value = "type")
        val type: PokemonTypeDTO
)