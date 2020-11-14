package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon DTO definition
 */
data class PokemonDTO (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)