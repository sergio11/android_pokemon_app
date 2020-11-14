package sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon

import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkException

/**
 * Pokemon Network Repository
 */
interface IPokemonNetworkRepository {

    /**
     * Get a pokemon page list
     * @param offset
     * @param limit
     */
    @Throws(NetworkException::class)
    suspend fun findPaginatedPokemonList(offset: Int, limit: Int): List<Pokemon>

    /**
     * Find Pokemon By Id
     * @param name
     */
    @Throws(NetworkException::class)
    suspend fun findPokemonByName(name: String): PokemonDetail

}