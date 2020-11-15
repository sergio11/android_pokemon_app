package sanchez.sanchez.sergio.androidpokeapi.domain.models


/**
 * Pokemon Detail Domain Model
 */
data class PokemonDetail (
    val id: Long,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val moves: List<PokemonMove>,
    val types: List<PokemonType>,
    val sprites: PokemonSprite,
    val stats: List<PokemonStat>
)