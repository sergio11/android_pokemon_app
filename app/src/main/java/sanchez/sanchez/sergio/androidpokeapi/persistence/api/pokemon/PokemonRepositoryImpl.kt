package sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon

import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoErrorException
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoNoResultException
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon.IPokemonDBRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkNoResultException
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.IPokemonNetworkRepository

/**
 * Pokemon Repository Implementation
 * @param pokemonNetworkRepository
 * @param pokemonDBRepository
 */
class PokemonRepositoryImpl (
    private val pokemonNetworkRepository: IPokemonNetworkRepository,
    private val pokemonDBRepository: IPokemonDBRepository
): IPokemonRepository {

    /**
     * Find Paginated Characters Order By Name Des
     * @param offset
     * @param limit
     */
    override suspend fun findPaginatedPokemonList(offset: Int, limit: Int): List<Pokemon> =
        try {
            pokemonNetworkRepository.findPaginatedList(offset, limit)
        } catch (ex: NetworkNoResultException) {
            throw RepoNoResultException(ex)
        } catch (ex: Exception) {
            throw RepoErrorException(ex)
        }


    /**
     * Find Pokemon detail By name
     * @param name
     */
    override suspend fun findPokemonDetailByName(name: String): PokemonDetail =
        try {
            pokemonDBRepository.findByName(name)
        } catch (ex: Exception) {
            // DB error, maybe pokemon doesn't in the local db
            try {
                pokemonNetworkRepository.findByName(name).also {
                    // Save pokemon into Database
                    pokemonDBRepository.save(it)
                }
            } catch (ex: NetworkNoResultException) {
                throw RepoNoResultException(ex)
            } catch (ex: Exception) {
                throw RepoErrorException(ex)
            }
        }
}