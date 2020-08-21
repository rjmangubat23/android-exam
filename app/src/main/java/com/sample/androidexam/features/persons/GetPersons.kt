package com.sample.androidexam.features.persons

import com.sample.androidexam.core.interactor.UseCase
import javax.inject.Inject

class GetPersons
@Inject constructor(private val personRepository: PersonRepository) : UseCase<PersonList, UseCase.None>() {

     override suspend fun run(params: None)= personRepository.getPersons()

}