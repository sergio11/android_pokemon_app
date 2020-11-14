package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon Ability DTO
 */
data class PokemonAbilityDTO (@field:Json(name = "name") val name: String)