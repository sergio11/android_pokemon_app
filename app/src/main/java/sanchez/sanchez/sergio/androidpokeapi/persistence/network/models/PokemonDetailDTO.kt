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
        val weight: Int,
        @field:Json(name = "abilities")
        val abilities: List<PokemonAbilityGroupDTO>,
        @field:Json(name = "types")
        val types: List<PokemonTypeSlotDTO>,
        /*@field:Json(name = "forms")
        val forms: List<PokemonFormDTO>,
        @field:Json(name = "moves")
        val moves: List<PokemonMoveDTO>,*/
        @field:Json(name = "sprites")
        val sprites: PokemonSpritesDTO
)