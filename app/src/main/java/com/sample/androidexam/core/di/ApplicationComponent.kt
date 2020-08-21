package com.sample.androidexam.core.di

import com.sample.androidexam.AndroidApplication
import com.sample.androidexam.core.di.viewmodel.ViewModelModule
import com.sample.androidexam.core.navigation.RouteActivity
import com.sample.androidexam.features.persons.PersonListFragment
import com.sample.androidexam.features.persons.details.PersonDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class, CacheModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(personListFragment: PersonListFragment)
    fun inject(personDetailsFragment: PersonDetailsFragment)
}
