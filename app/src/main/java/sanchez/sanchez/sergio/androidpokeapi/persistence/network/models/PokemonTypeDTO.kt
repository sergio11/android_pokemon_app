package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

data class PokemonTypeDTO (
        @field:Json(name = "name")
        val name: String
)