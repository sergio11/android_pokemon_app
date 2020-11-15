package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Detail DTO
 */
data class PokemonDetailDTO (
        @SerializedName(value = "id")
        val id: Long,
        @SerializedName(value = "name")
        val name: String,
        @SerializedName(value = "height")
        val height: Int,
        @SerializedName(value = "weight")
        val weight: Int,
        @SerializedName(value = "abilities")
        val abilities: List<PokemonAbilityGroupDTO>,
        @SerializedName(value = "types")
        val types: List<PokemonTypeSlotDTO>,
        @SerializedName(value = "moves")
        val moves: List<PokemonMoveSlotDTO>,
        @SerializedName(value = "stats")
        val stats: List<PokemonStatSlotDTO>,
        @SerializedName(value = "sprites")
        val sprites: PokemonSpritesDTO
)