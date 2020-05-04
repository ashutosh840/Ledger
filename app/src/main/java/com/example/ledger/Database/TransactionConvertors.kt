package com.example.ledger.Database

import androidx.room.TypeConverter
import com.example.ledger.type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

public class Convertors (){

    @TypeConverter
    fun fromType(type: type?):String? {
        if (type == null){
            return null
        }

        val gson=Gson()
        val _type:Type=object :TypeToken<type?>() {}.type
        return  gson.toJson(type,_type)
    }

    @TypeConverter
    fun toType(typeString: String): type?{

        if(typeString == null ){
            return null
        }

        val gson=Gson()
        val _type:Type=object :TypeToken<type?>(){}.type
        return  gson.fromJson<type>(typeString,_type)
    }


    /**
    @TypeConverter
    fun fromTransactionList(transaction: List<transaction?>?): String? {
        if (transaction == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<transaction?>?>() {}.type
        return gson.toJson(transaction, type)
    }

    @TypeConverter
    fun toTransactionList(transactionString: String?): List<transaction>? {
        if (transactionString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<transaction?>?>() {}.type
        return gson.fromJson<List<transaction>>(transactionString, type)
    }
     */


}