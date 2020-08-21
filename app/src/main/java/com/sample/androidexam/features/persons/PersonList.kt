package com.sample.androidexam.features.persons

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonList(
    @SerializedName("persons")
    val persons: List<Person>
) : Parcelable {
    companion object {
        fun empty() = PersonList(arrayListOf(Person.empty()))
    }

}