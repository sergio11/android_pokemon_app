package sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper

import sanchez.sanchez.sergio.androidpokeapi.domain.models.*
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.*

/**
 * Pokemon Network Mapper
 */
class PokemonNetworkMapper {

    /**
     * DTO to Model
     * @param dto
     * @return model
     */
    fun dtoToModel(dto: PokemonDTO): Pokemon {
        val id = dto.url.trimEnd('/').substringAfterLast("/").toLong()
        return Pokemon(
            id = id,
            name = dto.name,
            imageUrl = "https://pokeres.bastionbot.org/images/pokemon/${id}.png"
        )
    }


    /**
     * DTO list to DTO model
     * @param dtoList
     * @return model list
     */
    fun dtoToModel(dtoList: List<PokemonDTO>): List<Pokemon> =
        dtoList.map {
            dtoToModel(it)
        }.toList()

}