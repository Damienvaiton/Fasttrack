package com.iut.app.android.fasttrack.model.Rankings.DriverRanking

data class StandingsLists(
    val DriverStandings: List<DriverStanding>,
    val round: String,
    val season: String
)