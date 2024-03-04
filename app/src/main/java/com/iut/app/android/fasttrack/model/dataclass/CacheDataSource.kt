package com.iut.app.android.fasttrack.model.dataclass

import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.Driver
import com.iut.app.android.fasttrack.model.dataclass.schedule.Circuit
import com.iut.app.android.fasttrack.model.dataclass.schedule.Race
import com.iut.app.android.fasttrack.model.room.users.Fan
import timber.log.Timber

object CacheDataSource {

    var connected: Boolean? = false
    var FanConnected: Fan? = null

    var transitDriver: Driver? = null

    var selectedRaceShop: Race? = null

    var transitCircuit: Circuit? = null



    fun isConnected(): Boolean? {
        return connected
    }

    fun setConnected(bool: Boolean) {
        connected = bool
    }

    fun setCircuit(race : Circuit) : Boolean{
        transitCircuit = race
        Timber.d("setCircuit: %s in cache : ", transitCircuit?.circuitName)
        return true
    }

    fun getCircuit() : Circuit?{
        return transitCircuit
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

    fun setRaceSelected(race: Race) : Boolean{
        selectedRaceShop = race
        return true
    }

    fun getRaceSelected(): Race?{
        return selectedRaceShop
    }


}

