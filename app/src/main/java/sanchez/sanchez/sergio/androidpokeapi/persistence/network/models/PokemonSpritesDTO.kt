package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Sprites DTO
 */
data class PokemonSpritesDTO (
        @SerializedName(value = "back_default")
        val backDefault : String?,
        @SerializedName(value = "back_shiny")
        val backShiny : String?,
        @SerializedName(value = "front_default")
        val frontDefault : String?,
        @SerializedName(value = "front_shiny")
        val frontShiny : String?,
)