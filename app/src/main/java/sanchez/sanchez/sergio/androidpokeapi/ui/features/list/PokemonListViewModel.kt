package sanchez.sanchez.sergio.androidpokeapi.ui.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sanchez.sanchez.sergio.androidpokeapi.domain.interact.FindPaginatedPokemonListInteract
import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception.RepoNoResultException
import javax.inject.Inject

/**
 * Pokemon View Model
 */
class PokemonListViewModel @Inject constructor(
    private val findPaginatedPokemonListInteract: FindPaginatedPokemonListInteract
): ViewModel() {

    /**
     * Live Data - Definitions
     */

    private val _pokemonListState by lazy {
        MutableLiveData<PokemonListState>()
    }

    val pokemonListState: LiveData<PokemonListState> = _pokemonListState

    /**
     * Load Next Page
     */
    fun loadNextPage() = viewModelScope.launch {
        load(getNextOffset())
    }

    /**
     * Load Character List
     */
    fun load(offset: Int = 0) = viewModelScope.launch {

        findPaginatedPokemonListInteract.execute(
            params = FindPaginatedPokemonListInteract.Params(
                offset , DEFAULT_PAGE_LIMIT
            ),
            onSuccess = fun(pokemonList) {
                _pokemonListState.postValue(
                    PokemonListState.OnSuccess(offset, pokemonList)
                )
            },
            onError = fun(ex: Exception) {
                _pokemonListState.postValue(
                    if(ex is RepoNoResultException)
                        PokemonListState.OnNotFound
                    else
                        PokemonListState.OnError(ex))
            }
        )
    }

    /**
     * Private Methods
     */

    /**
     *  Get Next Offset
     */
    private fun getNextOffset() = _pokemonListState.value?.let {
        if(it is PokemonListState.OnSuccess)
            it.offset + DEFAULT_PAGE_LIMIT
        else
            DEFAULT_START_OFFSET
    } ?: DEFAULT_START_OFFSET


    companion object {

        private const val DEFAULT_PAGE_LIMIT = 20
        private const val DEFAULT_START_OFFSET = 0

    }
}

sealed class PokemonListState {

    /**
     * On Success
     * @param offset
     * @param pokemonList
     */
    data class OnSuccess(val offset: Int, val pokemonList: List<Pokemon>): PokemonListState()

    /**
     * On Error
     * @param ex
     */
    data class OnError(val ex: Exception): PokemonListState()

    /**
     * On Not Found
     */
    object OnNotFound: PokemonListState()

}