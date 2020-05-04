package com.example.ledger.pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.ledger.Database.account
import com.example.ledger.Database.accountsDatabase

import com.example.ledger.R
import com.example.ledger.type
import kotlinx.android.synthetic.main.fragment_create_account.*
import java.text.SimpleDateFormat
import java.util.*


class createAccount : Fragment() {


    private lateinit var viewModel: CommonViewModel

    lateinit var type:type


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        type=com.example.ledger.type.SNACK
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        val application = requireNotNull(this.activity).application
        val dataSource = accountsDatabase.getInstance(application)?.accountdao

        val viewModelFactory = dataSource?.let { CommonViewModelFactory(it,application) }
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CommonViewModel::class.java)

        val currentDate:String =
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime: String =
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        val time:String = "${currentDate} \t ${currentTime}"



        create_button.setOnClickListener(){
            val account=account(description_edittext_createaccount.text.toString(),
                time,
                type,
                amount_edittext_createamount.text.toString().toFloat()
            )
            viewModel._insert(account)
            Log.i("checkpoint","create account")
            view?.findNavController()?.navigate(R.id.action_createAccount_to_accountsPage)
            Log.i("checkpoint","create account")
        }

        cancel_button_createaccount.setOnClickListener(){

            view?.findNavController()?.navigate(R.id.action_createAccount_to_accountsPage)

        }






        /**
         *
         * ************************************************************************Spinner Part*****************************************************************************
         *
         */

        val spinner: Spinner = type_spinner_createaccount

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.type,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                type_spinner_createaccount.adapter = adapter
            }
        }



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Display the selected item text on text view

                when(parent.getItemAtPosition(position).toString()){
                    "Snacks" -> type=com.example.ledger.type.SNACK
                    "Entertainment"->type=com.example.ledger.type.ENTERTAINMENT
                    "Stationary"->type=com.example.ledger.type.STATIONARY
                    "Miscellaneous"->type=com.example.ledger.type.MISCELLANEOUS
                    "Other"->type=com.example.ledger.type.OTHER
                }


                Log.i("hi", "${type.toString()}")

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        /**
         *
         * ************************************************************************Spinner Part*****************************************************************************
         *
         */

    }


}
