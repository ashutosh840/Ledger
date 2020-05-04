package com.example.ledger.pages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ledger.Database.account
import com.example.ledger.Database.accountsDao
import com.example.ledger.Database.accountsDatabase
import kotlinx.coroutines.*

class CommonViewModel(val database: accountsDao, application: Application) :
    AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _currentAccount = MutableLiveData<account>()

    val currentAccount: LiveData<account>
        get() = _currentAccount

    val allAccounts = database.getAllAccounts()

    init {

        intialize()
    }

    private fun intialize() {
        uiScope.launch {
            _currentAccount.value = getFromDatabase()
        }
    }

    private suspend fun getFromDatabase(): account? {
        return withContext(Dispatchers.IO) {
            val account = database.getCurrentAccount()
            account
        }

    }

    fun _insert(account: account){

        uiScope.launch {

            insert(account)

        }

    }

    private suspend fun insert(account: account){

        withContext(Dispatchers.IO){

            database.insert(account)
          //  _currentAccount.value=getFromDatabase()
        }
    }



}
