package sanchez.sanchez.sergio.androidpokeapi.ui.features.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonSplashFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.factory.DaggerComponentFactory
import sanchez.sanchez.sergio.androidpokeapi.ui.MainActivity
import sanchez.sanchez.sergio.androidpokeapi.ui.core.SupportFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.core.ext.navigateAndFinish

/**
 * Pokemon Splash Fragment
 */
class PokemonSplashFragment: SupportFragment<PokemonSplashViewModel>(PokemonSplashViewModel::class.java)  {

    private val component: PokemonSplashFragmentComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getPokemonSplashFragmentComponent(requireActivity() as AppCompatActivity)
    }

    override fun layoutId(): Int = R.layout.splash_fragment_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.sessionState.observe(this, {
            when(it) {
                is SessionState.OnLoaded -> onSessionLoaded()
                is SessionState.OnNotFound -> onSessionNotFound()
            }
        })
    }

    override fun onInject() {
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.loadSession()
        }, 5000)
    }

    /**
     * Private Methods
     */

    private fun onSessionLoaded() {
        navigateAndFinish(MainActivity.createDestination(requireActivity()))
    }

    private fun onSessionNotFound() {
        // TODO: go to login screen
    }
}