package sanchez.sanchez.sergio.androidpokeapi.di.factory

import androidx.appcompat.app.AppCompatActivity
import sanchez.sanchez.sergio.androidpokeapi.AndroidPokeApiApp
import sanchez.sanchez.sergio.androidpokeapi.di.component.MainActivityComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.ApplicationComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.DaggerApplicationComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.SplashActivityComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonDetailFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonListFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonSplashFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ActivityModule
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ApplicationModule

/**
 * Dagger Component Factory
 */
object DaggerComponentFactory {

    private var appComponent: ApplicationComponent? = null
    private var mainActivityComponent: MainActivityComponent? = null
    private var splashActivityComponent: SplashActivityComponent? = null

    fun getAppComponent(app: AndroidPokeApiApp): ApplicationComponent =
        appComponent ?: DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(app))
            .build().also {
                appComponent = it
            }

    fun getMainActivityComponent(activity: AppCompatActivity): MainActivityComponent =
        mainActivityComponent ?: getAppComponent(activity.application as AndroidPokeApiApp)
            .mainActivityComponent(ActivityModule(activity)).also {
                mainActivityComponent = it
            }

    fun getSplashActivityComponent(activity: AppCompatActivity): SplashActivityComponent =
            splashActivityComponent ?: getAppComponent(activity.application as AndroidPokeApiApp)
                    .splashActivityComponent(ActivityModule(activity)).also {
                        splashActivityComponent = it
                    }

    fun getPokemonListFragmentComponent(activity: AppCompatActivity): PokemonListFragmentComponent =
        getMainActivityComponent(activity).pokemonFragmentComponent()

    fun getPokemonDetailFragmentComponent(activity: AppCompatActivity): PokemonDetailFragmentComponent =
        getMainActivityComponent(activity).pokemonDetailFragmentComponent()

    fun getPokemonSplashFragmentComponent(activity: AppCompatActivity): PokemonSplashFragmentComponent =
            getSplashActivityComponent(activity).pokemonSplashComponent()

}