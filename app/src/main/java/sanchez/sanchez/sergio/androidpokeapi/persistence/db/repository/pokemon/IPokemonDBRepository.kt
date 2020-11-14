package sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon

import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoErrorException

/**
 * Character DB Repository
 */
interface IPokemonDBRepository {

    @Throws(RepoErrorException::class)
    suspend fun deleteAll()

}