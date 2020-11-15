package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

data class PokemonTypeDTO (
        @SerializedName(value = "name")
        val name: String
)