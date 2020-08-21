package com.sample.androidexam.core.extension

import android.util.Patterns
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

fun String.isValidPhoneNo(): Boolean {
    return Patterns.PHONE.matcher(this).matches()
}

fun String.readableDate(): String {
    val date = LocalDate.parse(this, DateTimeFormatter.ofPattern("MM-dd-yyyy"))
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)
    return date.format(formatter)
}
