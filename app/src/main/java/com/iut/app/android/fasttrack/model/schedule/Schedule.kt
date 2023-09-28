package com.iut.app.android.fasttrack.model.schedule

data class Schedule(
    val Circuit: Circuit,
    val FirstPractice: FirstPractice,
    val Qualifying: Qualifying,
    val SecondPractice: SecondPractice,
    val ThirdPractice : ThirdPractice,
    val Sprint: Sprint,
    val date: String,
    val raceName: String,
    val round: String,
    val season: String,
    val time: String,
    val url: String
)