package com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking

import com.google.gson.annotations.SerializedName

data class ConstructorStanding(
    @SerializedName("Constructor") val constructor: Constructor,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)