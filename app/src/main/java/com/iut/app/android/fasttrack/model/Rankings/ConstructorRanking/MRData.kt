package com.iut.app.android.fasttrack.model.Rankings.ConstructorRanking

data class MRData(
    val StandingsTable: StandingsTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)