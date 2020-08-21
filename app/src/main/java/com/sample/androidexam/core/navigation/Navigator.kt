package com.sample.androidexam.core.navigation

import android.content.Context
import com.sample.androidexam.features.persons.Person
import com.sample.androidexam.features.persons.PersonListActivity
import com.sample.androidexam.features.persons.details.PersonDetailsActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showPersonList(context: Context) = context.startActivity(PersonListActivity.callingIntent(context))
    fun showPersonDetails(context: Context, person: Person) = context.startActivity(PersonDetailsActivity.callingIntent(context, person))

}

