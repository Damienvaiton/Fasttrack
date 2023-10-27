package com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking

import com.google.gson.annotations.SerializedName

data class DriverStanding(
  @SerializedName("Constructors")  val constructors: List<Constructor>,
  @SerializedName("Driver")  val driver: Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)