package com.example.ledger.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface accountsDao {
    @Insert
    fun insert(account: account)

    @Update
    fun update(account: account)

    @Delete
    fun delete(account: account)

    @Query("SELECT * FROM account_table where account_id = :id ")
    fun getAccount(id:Int): account?

    @Query("SELECT * FROM account_table")
    fun getAllAccounts():LiveData<List<account>>

    @Query("SELECT * FROM account_table ORDER BY account_id DESC LIMIT 1")
    fun getCurrentAccount():account?




}