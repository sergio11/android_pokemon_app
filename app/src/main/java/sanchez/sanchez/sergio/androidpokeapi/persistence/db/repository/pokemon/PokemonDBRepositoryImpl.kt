package sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.exception.DBErrorException
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.pokemon.IPokemonDAO
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.mapper.PokemonDBMapper

/**
 * Character DB Repository
 */
open class PokemonDBRepositoryImpl (
    private val pokemonDAO: IPokemonDAO,
    private val pokemonDBMapper: PokemonDBMapper
): IPokemonDBRepository {


    override suspend fun deleteAll() = withContext(Dispatchers.IO)  {
        try {
            pokemonDAO.deleteAll()
        } catch (ex: Exception) {
            throw DBErrorException("Error occurred when delete characters", ex)
        }
    }

}