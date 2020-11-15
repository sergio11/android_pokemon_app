package sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon

import io.ktor.client.*
import io.ktor.client.request.*
import sanchez.sanchez.sergio.androidpokeapi.BuildConfig
import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkNoResultException
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonDetailNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.APIResponse
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.PokemonDTO
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.PokemonDetailDTO
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.core.SupportNetworkRepository

/**
 * Pokemon Network Repository Impl
 * @param httpClient
 * @param pokemonNetworkMapper
 * @param pokemonDetailNetworkMapper
 */
open class PokemonNetworkRepositoryImpl(
    private val httpClient: HttpClient,
    private val pokemonNetworkMapper: PokemonNetworkMapper,
    private val pokemonDetailNetworkMapper: PokemonDetailNetworkMapper
): SupportNetworkRepository(), IPokemonNetworkRepository {


    /**
     * Find Paginated Pokemon List
     * @param offset
     * @param limit
     */
    override suspend fun findPaginatedList(offset: Int, limit: Int): List<Pokemon> = safeNetworkCall {

        val dataResult = httpClient.get<APIResponse<PokemonDTO>>("${BuildConfig.BASE_URL}pokemon") {
            parameter("offset", offset)
            parameter("limit", limit)
        }.data

        if(dataResult.isEmpty())
            throw NetworkNoResultException("Not Pokemon found")

        pokemonNetworkMapper.dtoToModel(dataResult)

    }

    /**
     * Find Pokemon By Name
     * @param name
     */
    override suspend fun findByName(name: String): PokemonDetail = safeNetworkCall {
        val dataResult = httpClient.get<PokemonDetailDTO>("${BuildConfig.BASE_URL}pokemon/$name")
        pokemonDetailNetworkMapper.dtoToModel(dataResult)
    }

}