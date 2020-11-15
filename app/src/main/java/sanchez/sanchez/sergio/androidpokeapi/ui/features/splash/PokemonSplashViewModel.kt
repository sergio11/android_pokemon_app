package sanchez.sanchez.sergio.androidpokeapi.ui.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Pokemon Splash View Model
 */
class PokemonSplashViewModel @Inject constructor() : ViewModel() {

    /**
     * Live Data - Definitions
      */

    private val _sessionState: MutableLiveData<SessionState> by lazy {
        MutableLiveData<SessionState>()
    }

    val sessionState: LiveData<SessionState> = _sessionState

    /**
     * Load Session
     */
    fun loadSession() = viewModelScope.launch {
        // TODO: Check if there is a active session
        _sessionState.postValue(SessionState.OnLoaded)
    }

}


/**
 * Session State
 */
sealed class SessionState {

    /**
     * On Loaded
     */
    object OnLoaded: SessionState()

    /**
     * On Not Found
     */
    object OnNotFound: SessionState()
}