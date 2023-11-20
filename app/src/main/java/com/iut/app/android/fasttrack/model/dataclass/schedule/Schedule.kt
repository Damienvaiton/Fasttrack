package com.iut.app.android.fasttrack.model.dataclass.schedule

import com.google.gson.annotations.SerializedName

data class Schedule(
  @SerializedName("MRData")  val mRData: MRData
)