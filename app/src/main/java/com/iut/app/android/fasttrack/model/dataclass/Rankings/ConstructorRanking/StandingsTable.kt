package com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking

import com.google.gson.annotations.SerializedName

data class StandingsTable(
   @SerializedName("standingsLists") val StandingsLists: List<StandingsLists>,
    val season: String
)