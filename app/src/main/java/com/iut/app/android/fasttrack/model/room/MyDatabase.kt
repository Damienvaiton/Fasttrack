package com.iut.app.android.fasttrack.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iut.app.android.fasttrack.model.room.Tickets.TicketsDao
import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import com.iut.app.android.fasttrack.model.room.users.User

@Database(entities = [Fan::class, User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {


    abstract fun getDao(): FanDAO


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null
        fun initDatabase(context: Context) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java, "fasttrack_database"
            ).build()
        }
        fun getDatabase(): MyDatabase? {
            return INSTANCE
        }
    }
}