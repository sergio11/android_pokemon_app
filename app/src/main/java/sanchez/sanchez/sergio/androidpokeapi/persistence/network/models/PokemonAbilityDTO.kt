package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Ability DTO
 */
data class PokemonAbilityDTO (@SerializedName(value = "name") val name: String)