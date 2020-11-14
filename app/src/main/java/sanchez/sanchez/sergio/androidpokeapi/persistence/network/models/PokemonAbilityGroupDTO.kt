package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon Ability Group DTO
 */
data class PokemonAbilityGroupDTO (
        @field:Json(name = "ability")
        val ability: PokemonAbilityDTO,
        @field:Json(name = "is_hidden")
        val isHidden: Boolean,
        @field:Json(name = "slot")
        val slot: Int,
)