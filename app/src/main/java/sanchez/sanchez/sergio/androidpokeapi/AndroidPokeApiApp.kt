package sanchez.sanchez.sergio.androidpokeapi

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class AndroidPokeApiApp: Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        if (BuildConfig.DEBUG) {
            onDebugConfig()
        } else {
            onReleaseConfig()
        }

    }

    /**
     * Private Methods
     */

    /**
     * On Debug Config
     */
    private fun onDebugConfig() {
        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
    }

    /**
     * On Release Config
     */
    private fun onReleaseConfig(){}

    companion object {

        @JvmStatic
        lateinit var INSTANCE: AndroidPokeApiApp

    }
}