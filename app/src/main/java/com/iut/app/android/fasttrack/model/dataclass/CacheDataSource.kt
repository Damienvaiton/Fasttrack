package com.iut.app.android.fasttrack.model.dataclass

import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.Driver

import com.iut.app.android.fasttrack.model.room.users.Fan

object CacheDataSource {

    var connected: Boolean? = false
    var FanConnected: Fan? = null

    var transitDriver : Driver? = null

    fun isConnected(): Boolean? {
        return connected
    }

    fun setConnected(bool: Boolean): Unit{
        connected = bool
    }

    fun setFanConnected(fan: Fan): Boolean{
        FanConnected = fan
        return true
    }

    fun getFan(): Fan?{
        return FanConnected
    }

    fun getDriver (): Driver?{
        return transitDriver
    }

    fun setDriver (driver: Driver): Unit{
        transitDriver = driver
    }








}

