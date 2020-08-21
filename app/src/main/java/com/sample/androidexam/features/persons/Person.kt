package com.sample.androidexam.features.persons

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sample.androidexam.core.extension.empty
import com.sample.androidexam.core.extension.noValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("mobile_num")
    val mobileNum: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("contact_person")
    val contactPerson: String,
    @SerializedName("contact_person_num")
    val contactPersonNum: String
) : Parcelable {
    companion object {
        fun empty() =
            Person(
                String.empty(),
                String.empty(),
                String.empty(),
                Int.noValue(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
    }
}