package com.sample.androidexam

import android.app.Application
import com.sample.androidexam.core.di.ApplicationComponent
import com.sample.androidexam.core.di.ApplicationModule
import com.sample.androidexam.core.di.CacheModule
import com.sample.androidexam.core.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .cacheModule(CacheModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
