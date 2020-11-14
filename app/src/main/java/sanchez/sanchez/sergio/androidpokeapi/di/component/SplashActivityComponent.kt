package sanchez.sanchez.sergio.androidpokeapi.di.component

import dagger.Subcomponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.core.ActivityComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonSplashFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ActivityModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerActivity
import sanchez.sanchez.sergio.androidpokeapi.ui.features.splash.SplashScreenActivity


@PerActivity
@Subcomponent(modules = [
    ActivityModule::class
])
interface SplashActivityComponent: ActivityComponent {

    fun inject(activity: SplashScreenActivity)

    fun pokemonSplashComponent(): PokemonSplashFragmentComponent
}