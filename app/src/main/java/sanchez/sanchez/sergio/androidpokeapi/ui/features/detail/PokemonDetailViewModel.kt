package sanchez.sanchez.sergio.androidpokeapi.ui.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sanchez.sanchez.sergio.androidpokeapi.domain.interact.FindPokemonDetailByNameInteract
import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import javax.inject.Inject

/**
 * Pokemon Detail View Model
 * @param findPokemonDetailByNameInteract
 */
class PokemonDetailViewModel @Inject constructor(
    private val findPokemonDetailByNameInteract: FindPokemonDetailByNameInteract
): ViewModel() {

    /**
     * Live Data - Definitions
     */

    private val _pokemonDetailState by lazy {
        MutableLiveData<PokemonDetailState>()
    }

    val pokemonDetailState: LiveData<PokemonDetailState> = _pokemonDetailState

    /**
     * Public Methods
     */

    /**
     * Load By Name
     * @param name
     */
    fun loadByName(name: String) = viewModelScope.launch {
        findPokemonDetailByNameInteract.execute(
            params = FindPokemonDetailByNameInteract.Params(name),
            onSuccess = fun(character) {
                _pokemonDetailState.postValue(
                    PokemonDetailState.OnSuccess(character)
                )
            },
            onError = fun(ex: Exception) {
                _pokemonDetailState.postValue(
                    PokemonDetailState.OnError(ex)
                )
            }
        )
    }

}

sealed class PokemonDetailState {

    /**
     * On Success
     * @param pokemon
     */
    data class OnSuccess(val pokemon: PokemonDetail): PokemonDetailState()

    /**
     * On Error
     * @param ex
     */
    data class OnError(val ex: Exception): PokemonDetailState()

}