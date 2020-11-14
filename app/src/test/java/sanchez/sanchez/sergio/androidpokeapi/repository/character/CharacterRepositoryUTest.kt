package sanchez.sanchez.sergio.androidpokeapi.repository.character

import android.os.Build
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.PokemonRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.pokemon.IPokemonRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoNoResultException
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon.PokemonDBRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.pokemon.IPokemonDBRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.AppRoomDatabase
import sanchez.sanchez.sergio.androidpokeapi.persistence.db.room.mapper.PokemonDBMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonDetailNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.mapper.PokemonNetworkMapper
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.PokemonNetworkRepositoryImpl
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.pokemon.IPokemonNetworkRepository
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.serder.DateJsonAdapter
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.service.IPokemonService
import java.io.File
import java.net.InetAddress

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TransactionRepositoryUTest {

    private lateinit var mockServer : MockWebServer
    private lateinit var database: AppRoomDatabase
    private lateinit var pokemonNetworkRepository: IPokemonNetworkRepository
    private lateinit var pokemonDBRepository: IPokemonDBRepository
    private lateinit var pokemonRepository: IPokemonRepository

    @Test
    fun test_repository_001_a_page_of_five_characters_order_by_name_is_retrieved_successfully(){
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 200 OK"
                    setBody(getJson("json/network/character/test_repository_001_a_page_of_five_characters_order_by_name_is_retrieved_successfully.json"))
                })

            val offset = 0
            val limit = 5

            val characters = pokemonRepository.findPaginatedPokemonList(offset, limit)

            assertThat(characters.isFromCache).isEqualTo(false)
            assertThat(characters.offset).isEqualTo(offset)
            assertThat(characters.limit).isEqualTo(limit)
            assertThat(characters.pokemonList).isNotEmpty
            assertThat(characters.pokemonList).hasSize(limit)
            assertThat(characters.pokemonList[0].name).isLessThan(characters.pokemonList[1].name)
            assertThat(characters.pokemonList[1].name).isLessThan(characters.pokemonList[2].name)
            assertThat(characters.pokemonList[2].name).isLessThan(characters.pokemonList[3].name)
            assertThat(characters.pokemonList[3].name).isLessThan(characters.pokemonList[4].name)

        }
    }

    @Test
    fun test_repository_002_a_page_of_five_characters_cannot_be_retrieved_the_first_time_from_the_cache() {
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 500 Internal Server Error"
                })

            try {
                val offset = 0
                val limit = 5
                pokemonRepository.findPaginatedPokemonList(offset, limit)
            } catch (ex: Exception) {
                // Check No Result Exception
                assertThat(ex).isInstanceOf(RepoNoResultException::class.java)
            }

        }
    }

    @Test
    fun test_repository_003_a_page_of_five_characters_can_be_retrieved_the_second_time_from_cache_in_order() {
        runBlocking {

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 200 OK"
                    setBody(getJson("json/network/character/test_repository_003_a_page_of_five_characters_can_be_retrieved_the_second_time_from_cache_in_order.json"))
                })

            val offset = 0
            val limit = 5
            pokemonRepository.findPaginatedPokemonList(offset, limit)

            mockServer.enqueue(
                MockResponse().apply {
                    status = "HTTP/1.1 500 Internal Server Error"
                })

            val characters = pokemonRepository.findPaginatedPokemonList(offset, limit)

            assertThat(characters.isFromCache).isEqualTo(true)
            assertThat(characters.pokemonList).isNotEmpty
            assertThat(characters.pokemonList).hasSize(limit)
            assertThat(characters.pokemonList[0].name).isLessThan(characters.pokemonList[1].name)
            assertThat(characters.pokemonList[1].name).isLessThan(characters.pokemonList[2].name)
            assertThat(characters.pokemonList[2].name).isLessThan(characters.pokemonList[3].name)
            assertThat(characters.pokemonList[3].name).isLessThan(characters.pokemonList[4].name)
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


    /**
     * Setup
     */
    @Before
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

        // Create Spy for network repository
        pokemonNetworkRepository = Mockito.spy(
            PokemonNetworkRepositoryImpl(
                characterService,
                PokemonNetworkMapper(),
                PokemonDetailNetworkMapper()
            )
        )

        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppRoomDatabase::class.java).build()

        // Create Spy for DB repository
        pokemonDBRepository = Mockito.spy(
            PokemonDBRepositoryImpl(
                database.pokemonDAO(),
                PokemonDBMapper()
            )
        )

        pokemonRepository = PokemonRepositoryImpl(
            pokemonNetworkRepository, pokemonDBRepository
        )

    }

    /**
     * Tear Down
     */
    @After
    @Throws
    fun tearDown() {
        database.close()
        mockServer.shutdown()
    }
}