package sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon

import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkNoResultException
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonDetailNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.core.SupportNetworkRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.service.IPokemonService

/**
 * Pokemon Network Repository Impl
 * @param pokemonService
 * @param pokemonNetworkMapper
 */
open class PokemonNetworkRepositoryImpl(
    private val pokemonService: IPokemonService,
    private val pokemonNetworkMapper: PokemonNetworkMapper,
    private val pokemonDetailNetworkMapper: PokemonDetailNetworkMapper
): SupportNetworkRepository(), IPokemonNetworkRepository {


    /**
     * Find Paginated Pokemon List
     * @param offset
     * @param limit
     */
    override suspend fun findPaginatedList(offset: Int, limit: Int): List<Pokemon> = safeNetworkCall {
        val dataResult = pokemonService.getPokemonList(offset, limit).data
        if(dataResult.isEmpty())
            throw NetworkNoResultException("Not Pokemon found")
        pokemonNetworkMapper.dtoToModel(dataResult)
    }

    /**
     * Find Pokemon By Name
     * @param name
     */
    override suspend fun findByName(name: String): PokemonDetail = safeNetworkCall {
        val dataResult =  pokemonService.getPokemonDetail(name)
        pokemonDetailNetworkMapper.dtoToModel(dataResult)
    }

}