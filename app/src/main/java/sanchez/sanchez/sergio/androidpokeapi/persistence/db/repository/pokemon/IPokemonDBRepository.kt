package sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon

import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoErrorException
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoNoResultException

/**
 * Character DB Repository
 */
interface IPokemonDBRepository {

    /**
     * Find By Name
     * @param name
     */
    @Throws(RepoNoResultException::class, RepoErrorException::class)
    suspend fun findByName(name: String): PokemonDetail

    /**
     * Save Pokemon
     * @param pokemon
     */
    @Throws(RepoErrorException::class)
    suspend fun save(pokemon: PokemonDetail)

    @Throws(RepoErrorException::class)
    suspend fun deleteAll()

}