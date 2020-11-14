package sanchez.sanchez.sergio.androidpokeapi.di.modules.core

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import sanchez.sanchez.sergio.androidpokeapi.di.modules.core.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}