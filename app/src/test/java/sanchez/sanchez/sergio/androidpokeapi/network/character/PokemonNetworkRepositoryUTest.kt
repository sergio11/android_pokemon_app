package sanchez.sanchez.sergio.androidpokeapi.network.character

import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkBadRequestException
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkForbiddenException
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.NetworkNoResultException
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonDetailNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.PokemonNetworkRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.IPokemonNetworkRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.serder.DateJsonAdapter
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.service.IPokemonService
import java.io.File
import java.net.InetAddress

/**
 * Test for Character Network Repository
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner::class)
class PokemonNetworkRepositoryUTest {


    @Test
    fun test_network_repository_001_get_two_characters_from_start(){
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 200 OK"
                    setBody(getJson("json/network/character/test_network_repository_001_get_two_characters_from_start.json"))
                })

            val offset = 0
            val limit = 2

            val characters = pokemonNetworkRepository.findPaginatedPokemonList(offset, limit)

            assertThat(characters.isFromCache).isEqualTo(false)
            assertThat(characters.offset).isEqualTo(offset)
            assertThat(characters.limit).isEqualTo(limit)
            assertThat(characters.pokemonList).isNotEmpty
            assertThat(characters.pokemonList).hasSize(limit)
        }
    }

    @Test
    fun test_network_repository_002_character_has_four_comic_and_two_series(){
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 200 OK"
                    setBody(getJson("json/network/character/test_network_repository_002_character_has_four_comic_and_two_series.json"))
                })

            val characterId = 1011334L
            val characterDetail = pokemonNetworkRepository.findPokemonByName(characterId)

            assertThat(characterDetail.id).isEqualTo(characterId)
            assertThat(characterDetail.comics.returned).isEqualTo(4)
            assertThat(characterDetail.series.returned).isEqualTo(2)

        }
    }

    @Test
    fun test_network_repository_003_character_not_exists(){
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 404 Not Found"
                })

            try {
                val characterId = 32321321321L
                pokemonNetworkRepository.findPokemonByName(characterId)
            } catch (ex: Exception) {
                assertThat(ex).isInstanceOf(NetworkNoResultException::class.java)
            }

        }
    }

    @Test
    fun test_network_repository_004_invalid_authentication(){
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 403 Forbidden"
                })

            try {
                val characterId = 1011334L
                pokemonNetworkRepository.findPokemonByName(characterId)
            } catch (ex: Exception) {
                assertThat(ex).isInstanceOf(NetworkForbiddenException::class.java)
            }

        }
    }

    @Test
    fun test_network_repository_005_bad_request(){
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 400 Bad Request"
                })

            try {
                val characterId = 1011334L
                pokemonNetworkRepository.findPokemonByName(characterId)
            } catch (ex: Exception) {
                assertThat(ex).isInstanceOf(NetworkBadRequestException::class.java)
            }

        }
    }


    /**
     * Private Methods
     */

    /**
     * Helper function which will loadByDate JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    private fun getJson(path: String) : String {
        // Load the JSON response
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }


    companion object {

        @JvmStatic
        lateinit var mockServer : MockWebServer

        @JvmStatic
        lateinit var pokemonNetworkRepository : IPokemonNetworkRepository

        @JvmStatic
        @BeforeClass
        @Throws
        fun setup() {
            // Initialize mock webserver
            mockServer = MockWebServer().also {
                // Start the local server
                it.start(InetAddress.getByName("127.0.0.1"), 63637)
            }

            val characterService = Retrofit.Builder()
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                    .add(DateJsonAdapter())
                    .build()))
                .baseUrl(mockServer.url("").toString())
                .client(OkHttpClient.Builder().build())
                .build()
                .create(IPokemonService::class.java)


            pokemonNetworkRepository = PokemonNetworkRepositoryImpl(
                characterService,
                PokemonNetworkMapper(),
                PokemonDetailNetworkMapper()
            )

        }

        @JvmStatic
        @AfterClass
        @Throws
        fun tearDown() {
            // We're done with tests, shut it down
            mockServer.shutdown()
        }
    }
}