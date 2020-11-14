package sanchez.sanchez.sergio.androidpokeapi.domain.interact

import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.IPokemonRepository

/**
 * Find paginated pokemon list use case
 */
class FindPaginatedPokemonListInteract(
    private val pokemonRepository: IPokemonRepository
) {

    /**
     * @param params
     */
    suspend fun execute(params: Params): List<Pokemon> =
        pokemonRepository.findPaginatedPokemonList(
            offset = params.offset,
            limit = params.limit
        )

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (pokemonList: List<Pokemon>) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(execute(params))
    } catch (ex: Exception) {
        onError(ex)
    }

    /**
     * Params
     */
    data class Params(
        val offset: Int,
        val limit: Int
    )
}