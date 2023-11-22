package com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking

import com.google.gson.annotations.SerializedName

data class MRData(
    @SerializedName("StandingsTable") val standingsTable: StandingsTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)