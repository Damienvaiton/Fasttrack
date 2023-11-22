package com.iut.app.android.fasttrack.model.dataclass.schedule.Results

data class Result(
    val Constructor: Constructor,
    val Driver: Driver,
    val FastestLap: FastestLap,
    val Time: TimeX,
    val grid: String,
    val laps: String,
    val number: String,
    val points: String,
    val position: String,
    val positionText: String,
    val status: String
)