package com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking

import com.google.gson.annotations.SerializedName

data class StandingsLists(
  @SerializedName("DriverStandings")  val driverStandings: List<DriverStanding>,
    val round: String,
    val season: String
)