package sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.mapper

import sanchez.sanchez.sergio.androidpokeapi.domain.models.*
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.entity.*

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
            height = model.height,
            imageUrl = model.imageUrl,
            abilities = model.abilities.map {
                PokemonAbilityEntity(
                    name = it.name,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            },
            types = model.types.map {
                PokemonTypeEntity(
                    name = it.name,
                    slot = it.slot
                )
            },
            moves = model.moves.map {
                PokemonMoveEntity(name = it.name)
            },
            sprites = PokemonSpriteEntity(
                    backDefault = model.sprites.backDefault,
                    backShiny = model.sprites.backShiny,
                    frontDefault = model.sprites.frontDefault,
                    frontShiny = model.sprites.frontShiny
            ),
            stats = model.stats.map {
                PokemonStatEntity(
                        name = it.name,
                        baseStat = it.baseStat,
                        effort = it.effort
                )
            }
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
            imageUrl = entity.imageUrl,
            weight = entity.weight,
            height = entity.height,
            abilities = entity.abilities.map {
                PokemonAbility(
                    name = it.name,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            },
            types = entity.types.map {
                PokemonType(
                    slot = it.slot,
                    name = it.name
                )
            },
            moves = entity.moves.map {
                PokemonMove(name = it.name)
            },
            sprites = PokemonSprite(
                    backDefault = entity.sprites.backDefault,
                    backShiny = entity.sprites.backShiny,
                    frontDefault = entity.sprites.frontDefault,
                    frontShiny = entity.sprites.frontShiny
            ),
            stats = entity.stats.map {
                PokemonStat(
                    name = it.name,
                    baseStat = it.baseStat,
                    effort = it.effort
                )
            }
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