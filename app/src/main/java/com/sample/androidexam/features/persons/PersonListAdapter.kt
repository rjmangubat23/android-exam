package com.sample.androidexam.features.persons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.androidexam.R
import kotlinx.android.synthetic.main.row_person.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class PersonListAdapter
@Inject constructor() : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {

    @Inject lateinit var context: Context
    internal var persons: List<Person> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    internal var clickListener: (Person) -> Unit = { person -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_person, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loan = persons[position]
        holder.bind(loan, clickListener)
    }

    override fun getItemCount() = persons.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person, clickListener: (Person) -> Unit) {
            itemView.txtName.text = context.getString(R.string.label_name_identifier, person.firstName, person.lastName)
            itemView.txtPhoneNum.text = person.mobileNum
            itemView.setOnClickListener { clickListener(person) }
        }
    }
}
