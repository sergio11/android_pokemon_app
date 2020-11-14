package sanchez.sanchez.sergio.androidpokeapi.domain.models


/**
 * Pokemon Detail Domain Model
 */
data class PokemonDetail (
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int
)