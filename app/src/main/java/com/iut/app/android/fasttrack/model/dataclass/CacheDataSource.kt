package com.iut.app.android.fasttrack.model.dataclass

import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.Driver
import com.iut.app.android.fasttrack.model.dataclass.schedule.Circuit
import com.iut.app.android.fasttrack.model.dataclass.schedule.Race

import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.viewModel.UserViewModel
import timber.log.Timber

object CacheDataSource {

    var connected: Boolean? = false
    var FanConnected: Fan? = null

    var transitDriver: Driver? = null

    var transitRace: Circuit? = null

    fun isConnected(): Boolean? {
        return connected
    }

    fun setConnected(bool: Boolean) {
        connected = bool
    }

    fun setCircuit(race : Circuit) : Boolean{
        transitRace = race
        Timber.d("setCircuit: %s in cache : ", transitRace?.circuitName)
        return true
    }

    fun setFanConnected(fan: Fan): Boolean {
        FanConnected = fan
        return true
    }

    fun getFan(): Fan? {
        return FanConnected
    }

    fun getDriver(): Driver? {
        return transitDriver
    }

    fun setDriver(driver: Driver) {
        transitDriver = driver
    }


}

