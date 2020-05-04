package com.example.ledger.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ledger.type

@Entity(tableName = "account_table")
data class account(

    @ColumnInfo(name="description")
    val description:String?,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "transaction")
    val type: type?,

    @ColumnInfo(name = "amount")
    val amount: Float

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id")
    var accountId: Int = 0
}