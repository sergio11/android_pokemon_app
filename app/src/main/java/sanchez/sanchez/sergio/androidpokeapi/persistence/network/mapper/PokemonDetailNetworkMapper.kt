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
            height = dto.height,
            weight = dto.weight
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