package com.iut.app.android.fasttrack.model.dataclass.schedule

import com.google.gson.annotations.SerializedName

data class Circuit(
   @SerializedName("Location") val location: Location,
    val circuitId: String,
    val circuitName: String,
    val url: String
)