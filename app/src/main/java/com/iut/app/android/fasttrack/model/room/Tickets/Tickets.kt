package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.iut.app.android.fasttrack.model.room.users.Fan
import java.util.jar.Attributes.Name


data class Circuit(
    var CircuitId: Int,
    var CircuitName: String,
    var CircuitLocation: String,
    var CircuitCountry: String,
    var CircuitLatitude: Double,
    var CircuitLongitude: Double,
)

data class Race(
    var circuit: Circuit,
    var date: String
)

@Entity(tableName = "ticketTable")
data class Tickets(
    @PrimaryKey(autoGenerate = true)
    var ticketsId: Int,
    var price: Int,
    var userId: Int,
    var raceId: Race,
    var nameGrandStand: String,
    var nameBlock: String,
    var numberBase: Int
)

