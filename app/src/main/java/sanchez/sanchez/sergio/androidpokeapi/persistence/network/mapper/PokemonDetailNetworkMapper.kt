package sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper

import sanchez.sanchez.sergio.androidpokeapi.domain.models.*
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.*

/**
 * Pokemon Network Mapper
 */
class PokemonDetailNetworkMapper {

    /**
     * DTO to Model
     * @param dto
     * @return model
     */
    fun dtoToModel(dto: PokemonDetailDTO): PokemonDetail =
        PokemonDetail(
            id = dto.id,
            name = dto.name,
            imageUrl = "https://pokeres.bastionbot.org/images/pokemon/${dto.id}.png",
            height = dto.height,
            weight = dto.weight,
            abilities = dto.abilities.map {
                PokemonAbility(
                    name = it.ability.name,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            },
            types = dto.types.map {
                PokemonType(
                    slot = it.slot,
                    name = it.type.name
                )
            },
            moves = dto.moves.map {
                PokemonMove(name = it.move.name)
            },
            stats = dto.stats.map {
                PokemonStat(
                    name = it.stat.name,
                    baseStat = it.baseStat,
                    effort = it.effort
                )
            },
            sprites = PokemonSprite(
                    backDefault = dto.sprites.backDefault,
                    backShiny = dto.sprites.backShiny,
                    frontDefault = dto.sprites.frontDefault,
                    frontShiny = dto.sprites.frontShiny
            )
        )

    /**
     * DTO list to DTO model
     * @param dtoList
     * @return model list
     */
    fun dtoToModel(dtoList: List<PokemonDetailDTO>): List<PokemonDetail> =
        dtoList.map {
            dtoToModel(it)
        }.toList()

}