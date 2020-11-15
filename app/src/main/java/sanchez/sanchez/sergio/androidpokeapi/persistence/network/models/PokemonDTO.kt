package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon DTO definition
 */
data class PokemonDTO (
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "url") val url: String
)