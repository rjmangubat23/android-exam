package com.sample.androidexam.core.di

import com.sample.androidexam.AndroidApplication
import com.sample.androidexam.core.di.viewmodel.ViewModelModule
import com.sample.androidexam.core.navigation.RouteActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    /*fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)*/
}
