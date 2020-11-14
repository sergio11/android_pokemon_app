package sanchez.sanchez.sergio.androidpokeapi.ui.features.splash

import androidx.appcompat.app.AppCompatActivity
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonSplashFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.factory.DaggerComponentFactory
import sanchez.sanchez.sergio.androidpokeapi.ui.core.SupportFragment

/**
 * Splash Fragment
 */
class SplashFragment: SupportFragment<SplashViewModel>(SplashViewModel::class.java)  {

    private val component: PokemonSplashFragmentComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getPokemonSplashFragmentComponent(requireActivity() as AppCompatActivity)
    }

    override fun layoutId(): Int = R.layout.splash_fragment_layout

    override fun onInject() {
        component.inject(this)
    }
}