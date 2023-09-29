package com.iut.app.android.fasttrack.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "birthday") val birthday: String?,

)


@Entity
data class Driver (
    @ColumnInfo(name = "UserId") val UserId: User?,
    @ColumnInfo(name = "DriverId") val DriverId: String?,
    @ColumnInfo(name = "race_number") val permanentNumber: Int?,
)

@Entity
data class Fan (
    @ColumnInfo(name = "fanId") val fanId: User?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "ticketId") val tickets: List<Ticket>?,
    @ColumnInfo(name = "FavoriteDriverId") val favoriteDriverId: Int?,
    @ColumnInfo(name = "FavoriteTeamId") val favoriteTeamId: Int?,
)

@Entity
data class Team (
   @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "Driver2Id") val Driver1Id: Int?,
    @ColumnInfo(name = "Driver1Id") val Driver2Id: Int?,

)
