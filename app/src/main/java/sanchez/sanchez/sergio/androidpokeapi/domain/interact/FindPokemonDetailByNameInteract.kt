package sanchez.sanchez.sergio.androidpokeapi.domain.interact

import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.IPokemonRepository

/**
 * Find Pokemon detail by name use case
 */
class FindPokemonDetailByNameInteract(
    private val pokemonRepository: IPokemonRepository
) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (pokemon: PokemonDetail) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        val character = pokemonRepository.findPokemonDetailByName(params.name)
        onSuccess(character)
    } catch (ex: Exception) {
        onError(ex)
    }

    /**
     * Params
     */
    data class Params(
        val name: String
    )
}