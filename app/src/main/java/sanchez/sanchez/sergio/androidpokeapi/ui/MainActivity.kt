package sanchez.sanchez.sergio.androidpokeapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.di.component.MainActivityComponent
import sanchez.sanchez.sergio.androidpokeapi.di.factory.DaggerComponentFactory


class MainActivity: AppCompatActivity() {

    private val activityComponent: MainActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getMainActivityComponent(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}