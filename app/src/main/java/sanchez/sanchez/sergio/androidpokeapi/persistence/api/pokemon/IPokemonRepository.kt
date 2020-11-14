package sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon

import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoErrorException
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoNoResultException

/**
 * Pokemon Repository
 */
interface IPokemonRepository {

    /**
     * Find Paginated Paginate Pokemon list
     * @param offset
     * @param limit
     */
    @Throws(RepoNoResultException::class, RepoErrorException::class)
    suspend fun findPaginatedPokemonList(offset: Int, limit: Int): List<Pokemon>

    /**
     * Find Pokemon detail By name
     * @param name
     */
    @Throws(RepoNoResultException::class, RepoErrorException::class)
    suspend fun findPokemonDetailByName(name: String): PokemonDetail

}