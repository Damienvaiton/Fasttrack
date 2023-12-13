package com.iut.app.android.fasttrack

import android.app.Application
import com.iut.app.android.fasttrack.model.room.MyDatabase
import timber.log.Timber

class Fasttrack : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        MyDatabase.initDatabase(context = applicationContext)
    }
}