package com.iut.app.android.fasttrack.model.dataclass.schedule

import com.google.gson.annotations.SerializedName

data class Race(
   @SerializedName("Circuit") val circuit: Circuit,
   @SerializedName("FirstPractice")  val firstPractice: FirstPractice?,
   @SerializedName("Qualifying")  val qualifying: Qualifying,
   @SerializedName("SecondPractice")  val secondPractice: SecondPractice?,
   @SerializedName("Sprint")  val sprint: Sprint,
   @SerializedName("ThirdPractice")  val thirdPractice: ThirdPractice?,
    val date: String,
    val raceName: String,
    val round: String,
    val season: String,
    val time: String,
)

