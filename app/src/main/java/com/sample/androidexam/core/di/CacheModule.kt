package com.sample.androidexam.core.di

import com.sample.androidexam.AndroidApplication
import com.sample.androidexam.core.platform.Cache
import com.sample.androidexam.core.platform.Constants.STORE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule(private val application: AndroidApplication) {
    @Provides @Singleton fun providesCache(): Cache = Cache(STORE, application)
}