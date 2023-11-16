package com.iut.app.android.fasttrack

import android.app.Application
import com.iut.app.android.fasttrack.model.room.MyDatabase

class Fasttrack : Application() {

    override fun onCreate() {
        super.onCreate()


        MyDatabase.initDatabase(context = applicationContext)
    }
}