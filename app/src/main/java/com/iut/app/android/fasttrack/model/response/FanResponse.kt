package com.iut.app.android.fasttrack.model.response

import com.iut.app.android.fasttrack.model.enums.FanErrors
import com.iut.app.android.fasttrack.model.room.users.Fan

class FanResponse {
    private var fan: Fan? = null
    private var fanError: ArrayList<FanErrors> = arrayListOf()
    fun getFan(): Fan? {
        return fan
    }

    fun setFan(fan: Fan) {
        this.fan = fan
    }

    fun getFanError(): ArrayList<FanErrors> {
        return fanError
    }

    fun setFanError(fanError: ArrayList<FanErrors>) {
        this.fanError = fanError
    }

    fun addFanError(fanError: FanErrors) {
        this.fanError.add(fanError)
    }

    fun failed(): Boolean {
        return fanError.size > 0
    }

    fun success(): Boolean {
        return fanError.size == 0
    }

}