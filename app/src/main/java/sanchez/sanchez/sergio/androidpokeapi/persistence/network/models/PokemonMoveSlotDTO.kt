package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Move DTO
 */
data class PokemonMoveSlotDTO (
        @SerializedName(value = "move")
        val move: PokemonMoveDTO
)