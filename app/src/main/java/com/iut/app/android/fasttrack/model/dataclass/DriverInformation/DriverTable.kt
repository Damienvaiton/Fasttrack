package com.iut.app.android.fasttrack.model.dataclass.DriverInformation

import com.google.gson.annotations.SerializedName

data class DriverTable(
   @SerializedName("Drivers") val drivers: List<Driver>,
    val driverId: String
)