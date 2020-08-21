package com.sample.androidexam.features.persons

import androidx.lifecycle.MutableLiveData
import com.sample.androidexam.core.interactor.UseCase
import com.sample.androidexam.core.platform.BaseViewModel
import com.sample.androidexam.core.platform.Cache
import javax.inject.Inject

class PersonViewModel
@Inject constructor(private val getPersons: GetPersons) : BaseViewModel() {

    @Inject lateinit var cache: Cache
    var persons : MutableLiveData<PersonView> = MutableLiveData()

    fun getPersons() = getPersons(UseCase.None()){ it.fold(::handleFailure, ::handlePersonList) }

    private fun handlePersonList(personList: PersonList) {
        this.persons.value = PersonView(personList)
    }
}