package com.sample.androidexam.features.persons

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.sample.androidexam.R
import com.sample.androidexam.core.platform.BaseActivity

class PersonListActivity : BaseActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, PersonListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_up, android.R.anim.fade_out)
    }

    override fun fragment() = PersonListFragment()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}