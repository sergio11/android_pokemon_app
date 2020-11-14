package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.pokemon

import androidx.room.Dao
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.dao.core.SupportDAO
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity.PokemonEntity

/**
 * Pokemon DAO Impl
 */
@Dao
abstract class PokemonDAOImpl: SupportDAO<PokemonEntity>(), IPokemonDAO