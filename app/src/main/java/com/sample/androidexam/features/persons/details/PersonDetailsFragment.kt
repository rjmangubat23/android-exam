package com.sample.androidexam.features.persons.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sample.androidexam.R
import com.sample.androidexam.core.extension.readableDate
import com.sample.androidexam.core.navigation.Navigator
import com.sample.androidexam.core.platform.BaseFragment
import com.sample.androidexam.features.persons.Person
import kotlinx.android.synthetic.main.fragment_person_details.*
import kotlinx.android.synthetic.main.item_person.*
import javax.inject.Inject

class PersonDetailsFragment : BaseFragment() {

    companion object {
        private const val PERSON_PARAM = "PERSON_PARAM"
        fun forDetails(person: Person): PersonDetailsFragment {
            val loanDetailsFragment = PersonDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PERSON_PARAM, person)
            loanDetailsFragment.arguments = arguments
            return loanDetailsFragment
        }
    }

    @Inject lateinit var navigator: Navigator

    private var person : Person? = null

    override fun layoutId() = R.layout.fragment_person_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        person = arguments?.get(PERSON_PARAM) as Person
        showDetails()
    }

    private fun showDetails() {
        person?.let {
            // Details
            txtName.text = getString(R.string.label_name_identifier, it.firstName, it.lastName)
            txtAddress.text = it.address
            txtBirthday.text = it.birthday.readableDate()
            txtAge.text =  getString(R.string.label_age_identifier, it.age)
            txtEmail.text = it.email
            txtMobileNum.text = it.mobileNum
            txtContactPerson.text = it.contactPerson
            txtContactPersonNum.text = it.contactPersonNum
            // Listeners
            txtEmail.setOnClickListener {_ -> sendEmail(it.email, "Hello ${it.firstName}") }
            txtMobileNum.setOnClickListener {_ -> dialPhone(it.mobileNum) }
            txtContactPersonNum.setOnClickListener {_ -> dialPhone(it.contactPersonNum) }
        }
    }

    private fun sendEmail(recipient: String, subject: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val intent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)

        try {
            //start email intent
            startActivity(Intent.createChooser(intent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun dialPhone(phoneNumber: String) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))
    }
}