package sanchez.sanchez.sergio.androidpokeapi.di.component

import dagger.Subcomponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonDetailFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonListFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.component.core.ActivityComponent
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ActivityModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerActivity
import sanchez.sanchez.sergio.androidpokeapi.ui.MainActivity


@PerActivity
@Subcomponent(modules = [
    ActivityModule::class
])
interface MainActivityComponent: ActivityComponent {

    fun inject(activity: MainActivity)

    fun pokemonFragmentComponent(): PokemonListFragmentComponent

    fun pokemonDetailFragmentComponent(): PokemonDetailFragmentComponent

}