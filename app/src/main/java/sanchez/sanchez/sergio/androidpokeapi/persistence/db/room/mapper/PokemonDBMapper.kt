package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.mapper

import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity.PokemonEntity

/**
 * Mapper for Pokemon Entity
 */
class PokemonDBMapper {

    /**
     * Model To Entity
     * @param model
     * @return entity
     */
    fun modelToEntity(model: PokemonDetail): PokemonEntity =
        PokemonEntity(
            id = model.id,
            name = model.name,
            weight = model.weight,
            height = model.height
        )

    /**
     * Model List To Entity List
     * @param modelList
     * @return entityList
     */
    fun modelToEntity(modelList: List<PokemonDetail>): List<PokemonEntity> =
        modelList.map {
            modelToEntity(it)
        }.toList()

    /**
     * Entity To Model
     * @param entity
     * @return model
     */
    fun entityToModel(entity: PokemonEntity): PokemonDetail =
        PokemonDetail(
            id = entity.id,
            name = entity.name,
            weight = entity.weight,
            height = entity.height
        )

    /**
     * Entity to Model
     * @param entityList
     */
    fun entityToModel(entityList: List<PokemonEntity>): List<PokemonDetail> =
        entityList.map {
            entityToModel(it)
        }.toList()

}