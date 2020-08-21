package com.sample.androidexam.features.persons

data class PersonEntity(private val persons : List<Person>) {
    companion object {
        fun empty() = PersonEntity(arrayListOf())
    }

    fun toPersonList() = PersonList(persons)
}