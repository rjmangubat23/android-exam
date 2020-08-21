package com.sample.androidexam.features.persons

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonView(val personList : PersonList) : Parcelable
