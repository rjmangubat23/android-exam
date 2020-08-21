package com.sample.androidexam.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.androidexam.AndroidApplication
import com.sample.androidexam.core.di.ApplicationComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showPersonList(this)
    }
}
