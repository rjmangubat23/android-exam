package com.sample.androidexam.features.persons.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.sample.androidexam.R
import com.sample.androidexam.core.navigation.Navigator
import com.sample.androidexam.core.platform.BaseActivity
import com.sample.androidexam.features.persons.Person
import javax.inject.Inject

class PersonDetailsActivity : BaseActivity() {
    companion object {
        private const val INTENT_PERSON_PARAM = "INTENT_PERSON_PARAM"
        fun callingIntent(context: Context, person : Person) : Intent{
            val intent = Intent(context, PersonDetailsActivity::class.java)
            intent.putExtra(INTENT_PERSON_PARAM, person)
            return intent
        }
    }

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.fade_out)
        showBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun fragment() = PersonDetailsFragment.forDetails(intent.getParcelableExtra(INTENT_PERSON_PARAM))

}