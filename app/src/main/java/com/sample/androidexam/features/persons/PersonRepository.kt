package com.sample.androidexam.features.persons

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.androidexam.core.exception.Failure
import com.sample.androidexam.core.functional.Either
import com.sample.androidexam.core.functional.Either.Left
import com.sample.androidexam.core.functional.Either.Right
import com.sample.androidexam.core.platform.Cache
import com.sample.androidexam.core.platform.Constants.PERSONS
import com.sample.androidexam.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface PersonRepository {
    fun getPersons(): Either<Failure, PersonList>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: PersonService
    ) : PersonRepository {
        @Inject lateinit var cache: Cache
        override fun getPersons(): Either<Failure, PersonList> {
            return when (networkHandler.isConnected) {
                true -> {
                    val personListLoaded = getPersonListCache()
                    personListLoaded?.let {
                        // Load cache if not empty
                        Right(it)
                    } ?: run {
                        request(service.getPersons(), {
                            // Save to cache
                            cache.setString(PERSONS, Gson().toJson(it.toPersonList()))
                            // Transform person list response
                            it.toPersonList()
                        }, PersonEntity.empty())
                    }
                }
                false, null -> {
                    val personListOffline = getPersonListCache()
                    personListOffline?.let {
                        // Load cache offline if not empty
                        Right(it)
                    } ?: run {
                        Left(Failure.NetworkConnection)
                    }
                }
            }
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                exception.printStackTrace()
                Left(Failure.ServerError)
            }
        }

        private fun getPersonListCache() : PersonList? {
            val personJson: String? = cache.getString(PERSONS)
            return Gson().fromJson(personJson, object : TypeToken<PersonList>(){}.type)
        }
    }

}