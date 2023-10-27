package com.iut.app.android.fasttrack.model.dataclass.DriverInformation

data class Driver(
    val code: String,
    val dateOfBirth: String,
    val driverId: String,
    val familyName: String,
    val givenName: String,
    val nationality: String,
    val permanentNumber: String?,
    val url: String
)