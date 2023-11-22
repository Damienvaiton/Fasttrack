package com.iut.app.android.fasttrack.model.dataclass.schedule.Results

import com.google.gson.annotations.SerializedName

data class RaceTable(
   @SerializedName("Races") val races: List<Race>,
    val season: String
)