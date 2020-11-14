package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.pokemon

import androidx.room.Query
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.core.ISupportDAO
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity.PokemonEntity

/**
 * Pokemon DAO
 */
interface IPokemonDAO: ISupportDAO<PokemonEntity> {

    @Query("DELETE FROM pokemons")
    fun deleteAll()
}