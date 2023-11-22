package com.iut.app.android.fasttrack.model.dataclass.schedule.Results

data class FastestLap(
    val AverageSpeed: AverageSpeed,
    val Time: Time,
    val lap: String,
    val rank: String
)