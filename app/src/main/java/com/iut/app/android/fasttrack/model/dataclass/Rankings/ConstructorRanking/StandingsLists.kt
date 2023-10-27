package com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking

import com.google.gson.annotations.SerializedName

data class StandingsLists(
   @SerializedName("ConstructorStandings") val constructorStandings : List<ConstructorStanding>,
    val round: String,
    val season: String
)