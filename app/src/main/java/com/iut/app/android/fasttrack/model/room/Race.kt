package com.iut.app.android.fasttrack.model.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Ticket (
    @PrimaryKey val id: Int,
    @ColumnInfo(name= "price" ) val price: Int?,
    @ColumnInfo(name = "RaceId") val RaceId: Int?,
    @ColumnInfo(name = "GrandStrandId") val GrandStrandId: Int?,
    @ColumnInfo(name = "BlockId") val BlockId: Int?,
)

@Entity
data class Circuit (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "lat") val lat: Double?,
    @ColumnInfo(name = "lng") val lng: Double?,
)

@Entity
data class GrandStrand (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "CircuitId") val CircuitId: Int?,
)

@Entity
data class Race (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "time") val time: String?,
    @ColumnInfo(name = "CircuitId") val CircuitId: Int?,
)
