package com.iut.app.android.fasttrack.model.dataclass.DriverInformation

import com.google.gson.annotations.SerializedName

data class MRData(
    @SerializedName("DriverTable") val DriverTable: DriverTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)