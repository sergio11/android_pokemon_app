package sanchez.sanchez.sergio.androidpokeapi.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.di.component.MainActivityComponent
import sanchez.sanchez.sergio.androidpokeapi.di.factory.DaggerComponentFactory
import sanchez.sanchez.sergio.androidpokeapi.ui.core.ext.createDestination

/**
 * Main Activity
 */
class MainActivity: AppCompatActivity() {

    private val activityComponent: MainActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getMainActivityComponent(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    companion object {

        @JvmStatic
        fun createDestination(activity: Activity): ActivityNavigator.Destination =
                activity.createDestination(MainActivity::class.java)
    }

}