package sanchez.sanchez.sergio.androidpokeapi.di.modules.core

import android.content.Context
import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.androidpokeapi.AndroidPokeApiApp
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerApplication

/**
 * Application Module
 */
@Module
class ApplicationModule constructor(private val application: AndroidPokeApiApp) {

    /**
     * Provide Application Context
     * @return
     */
    @Provides
    @PerApplication
    fun provideApplicationContext(): Context = application

}