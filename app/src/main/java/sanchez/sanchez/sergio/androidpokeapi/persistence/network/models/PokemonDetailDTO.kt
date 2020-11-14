package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Pokemon Detail DTO
 */
data class PokemonDetailDTO (
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "weight")
    val weight: Int
)