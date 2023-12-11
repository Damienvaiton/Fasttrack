package com.iut.app.android.fasttrack.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.iut.app.android.fasttrack.model.room.Tickets.TicketsDao
import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import com.iut.app.android.fasttrack.utils.converters.Converters


@Database(entities = [Fan::class, Tickets::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {


    abstract fun getFanDao(): FanDAO
    abstract fun getTicketsDao(): TicketsDao


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