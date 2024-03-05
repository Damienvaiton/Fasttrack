package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ticketTable")
data class Tickets(
    @PrimaryKey(autoGenerate = true)
    var ticketsId: Int,
    var price: Int,
    var userId: Int,
    var raceId: String,
    var nameGrandStand: String,
    var nameBlock: String,
    var namePlace: String
)

