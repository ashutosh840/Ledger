package com.example.ledger.Database

import android.content.Context
import androidx.room.*

@Database(entities= [account::class], version = 4,exportSchema = false)
@TypeConverters(Convertors::class)
abstract class accountsDatabase : RoomDatabase() {

    abstract val accountdao: accountsDao

    companion object {

        @Volatile
        private var INSTANCE: accountsDatabase? = null


        fun getInstance(context: Context): accountsDatabase? {

            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        accountsDatabase::class.java,
                        "saccount_history_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }


}
