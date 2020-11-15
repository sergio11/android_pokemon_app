package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Stat DTO
 */
data class PokemonStatDTO (
    @SerializedName(value = "name")
    val name: String
)