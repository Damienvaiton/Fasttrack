package com.iut.app.android.fasttrack.model.dataclass.schedule.Results

import com.google.gson.annotations.SerializedName

data class Race(
  @SerializedName("Circuit")  val circuit: Circuit,
  @SerializedName("Results")  val results: List<Result>,
    val date: String,
    val raceName: String,
    val round: String,
    val season: String,
    val time: String,
    val url: String
)