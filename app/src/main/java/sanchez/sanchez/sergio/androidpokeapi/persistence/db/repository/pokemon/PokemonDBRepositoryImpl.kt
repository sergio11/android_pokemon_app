package sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
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

    /**
     * Find By Name
     * @param name
     */
    override suspend fun findByName(name: String): PokemonDetail = withContext(Dispatchers.IO)  {
        try {
            val pokemonEntity = pokemonDAO.findByName(name).first()
            pokemonDBMapper.entityToModel(pokemonEntity)
        } catch (ex: Exception) {
            throw DBErrorException("Error occurred when search pokemon in DB", ex)
        }
    }

    /**
     * Save Pokemon
     * @param pokemon
     */
    override suspend fun save(pokemon: PokemonDetail): Unit = withContext(Dispatchers.IO)  {
        try {
            pokemonDAO.upsert(pokemonDBMapper.modelToEntity(pokemon))
        } catch (ex: Exception) {
            throw DBErrorException("Error occurred when search pokemon in DB", ex)
        }
    }


    override suspend fun deleteAll() = withContext(Dispatchers.IO)  {
        try {
            pokemonDAO.deleteAll()
        } catch (ex: Exception) {
            throw DBErrorException("Error occurred when delete characters", ex)
        }
    }

}