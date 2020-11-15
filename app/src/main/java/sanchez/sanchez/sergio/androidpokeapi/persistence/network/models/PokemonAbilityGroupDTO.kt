package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Ability Group DTO
 */
data class PokemonAbilityGroupDTO (
        @SerializedName(value = "ability")
        val ability: PokemonAbilityDTO,
        @SerializedName(value = "is_hidden")
        val isHidden: Boolean,
        @SerializedName(value = "slot")
        val slot: Int,
)