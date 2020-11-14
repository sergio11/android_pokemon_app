package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon Move DTO
 */
data class PokemonMoveDTO (
        @field:Json(name = "name")
        val name: String
)