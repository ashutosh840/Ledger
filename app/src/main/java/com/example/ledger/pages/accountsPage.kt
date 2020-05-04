package com.example.ledger.pages

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ledger.Database.account
import com.example.ledger.Database.accountsDatabase

import com.example.ledger.R
import kotlinx.android.synthetic.main.accounts_page_fragment.*
import kotlinx.android.synthetic.main.content_main.*

class accountsPage : Fragment(),accountsPageAdapter.onItemClickListener {

    companion object {
        fun newInstance() = accountsPage()
    }

    private lateinit var viewModel: CommonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.accounts_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = accountsDatabase.getInstance(application)?.accountdao

        val viewModelFactory = dataSource?.let { CommonViewModelFactory(it, application) }
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CommonViewModel::class.java)

        /**
         * Instanstiating the recycler view and its adapter
         */
        val recyclerView = my_recycler_view
        val adapter = accountsPageAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        floatingActionButton.setOnClickListener() {

            view?.findNavController()?.navigate(R.id.action_accountsPage_to_createAccount)
        }

        viewModel.allAccounts.observe(viewLifecycleOwner, Observer {
            adapter.setAccount(it)
        })




    }

    override fun onItemClick(account: account, view: View) {

       registerForContextMenu(view)
        /**
         * Work needs to be done
         * #Update
         * #Delete
         */

    }


}
