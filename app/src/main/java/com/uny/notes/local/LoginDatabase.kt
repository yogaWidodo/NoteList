package com.uny.notes.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Login::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {
    companion object {

        private var INSTANCE: LoginDatabase? = null
        fun getDatabase(context: Context): LoginDatabase? {
            if (INSTANCE == null) {
                synchronized(LoginDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java,
                        "user_database"
                    )
                        .build()
                }
            }
            return INSTANCE

        }

    }

    abstract fun userDao(): LoginDao

}