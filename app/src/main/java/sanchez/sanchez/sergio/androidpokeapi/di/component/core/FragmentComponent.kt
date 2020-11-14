package sanchez.sanchez.sergio.androidpokeapi.di.component.core

import dagger.Component
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.ViewModelModule
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerFragment

@PerFragment
@Component(
    dependencies = [ ActivityComponent::class ],
    modules = [ViewModelModule::class])
interface FragmentComponent {

}