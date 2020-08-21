package com.sample.androidexam.features.persons

import retrofit2.Call
import retrofit2.http.GET

internal interface PersonApi {
    @GET("4759b67f-b1ad-4101-bdf7-e36ed3cc5872")
    fun getPersons(): Call<PersonEntity>
}