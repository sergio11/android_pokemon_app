package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Form DTO
 */
data class PokemonFormDTO (
        @SerializedName(value = "name")
        val name : String
)