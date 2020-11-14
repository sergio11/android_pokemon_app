package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon Sprites DTO
 */
data class PokemonSpritesDTO (
        @field:Json(name = "back_default")
        val backDefault : String?,
        @field:Json(name = "front_default")
        val frontDefault : String?
)