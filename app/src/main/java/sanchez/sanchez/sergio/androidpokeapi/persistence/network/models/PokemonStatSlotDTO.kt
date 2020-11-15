package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Pokemon Stat Slot DTO
 *
 */
data class PokemonStatSlotDTO (
    @SerializedName(value = "base_stat")
    val baseStat: Int,
    @SerializedName(value = "effort")
    val effort: Int,
    @SerializedName(value = "stat")
    val stat: PokemonStatDTO
)