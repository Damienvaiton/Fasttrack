package com.iut.app.android.fasttrack.model.dataclass.schedule

import com.google.gson.annotations.SerializedName

data class MRData(
  @SerializedName("RaceTable")  val raceTable: RaceTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)