package com.sample.androidexam.features.persons

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.androidexam.R
import com.sample.androidexam.core.exception.Failure
import com.sample.androidexam.core.extension.failure
import com.sample.androidexam.core.extension.observe
import com.sample.androidexam.core.extension.viewModel
import com.sample.androidexam.core.navigation.Navigator
import com.sample.androidexam.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_person_list.*
import javax.inject.Inject

class PersonListFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var personListAdapter: PersonListAdapter

    private lateinit var personViewModel: PersonViewModel

    override fun layoutId() = R.layout.fragment_person_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        personViewModel = viewModel(viewModelFactory) {
            observe(persons, ::handlePersonList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onResume() {
        super.onResume()
        showLoading()
        personViewModel.getPersons()
    }

    private fun initializeView() {
        rvPersonList.layoutManager = LinearLayoutManager(requireContext())
        rvPersonList.adapter = personListAdapter
        personListAdapter.clickListener = { person ->
            navigator.showPersonDetails(requireContext(), person)
        }
    }

    private fun handlePersonList(personView: PersonView?) {
        personView?.let {
            hideLoading()
            personListAdapter.persons = it.personList.persons
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(message: Int) {
        hideLoading()
        notify(message)
    }

    private fun showLoading(){
        pbLoading.visibility = VISIBLE
        rvPersonList.visibility = GONE
    }
    private fun hideLoading(){
        pbLoading.visibility = GONE
        rvPersonList.visibility = VISIBLE
    }
}