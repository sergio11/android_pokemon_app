package sanchez.sanchez.sergio.androidpokeapi.persistence.network.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.APIResponse
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.PokemonDTO
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.models.PokemonDetailDTO

/**
 * Pokemon API
 * ==================
 */

interface IPokemonService {

    /**
     * Get Pokemon List
     * @param offset
     * @param limit
     */
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset : Int = 0,
        @Query("limit") limit : Int = 20) : APIResponse<PokemonDTO>

    /**
     * Get Pokemon Detail
     * @param name
     */
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name : String) : PokemonDetailDTO
}