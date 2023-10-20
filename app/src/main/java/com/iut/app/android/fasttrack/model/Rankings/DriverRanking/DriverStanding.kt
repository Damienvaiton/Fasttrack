package com.iut.app.android.fasttrack.model.Rankings.DriverRanking

data class DriverStanding(
    val Constructors: List<Constructor>,
    val Driver: Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)