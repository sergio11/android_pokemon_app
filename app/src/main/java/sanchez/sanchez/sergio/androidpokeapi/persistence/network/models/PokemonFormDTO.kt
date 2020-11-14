package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon Form DTO
 */
data class PokemonFormDTO (
        @field:Json(name = "name")
        val name : String
)