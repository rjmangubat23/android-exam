package com.sample.androidexam.features.persons

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonService
@Inject constructor(retrofit: Retrofit) : PersonApi {
    private val personApi by lazy { retrofit.create(PersonApi::class.java) }
    override fun getPersons() = personApi.getPersons()
}