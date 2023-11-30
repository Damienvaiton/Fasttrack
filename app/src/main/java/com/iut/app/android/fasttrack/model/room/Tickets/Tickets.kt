package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.jar.Attributes.Name


data class Circuit (
         var CircuitId : Int,
        var CircuitName : String,
        var CircuitLocation : String,
        var CircuitCountry : String,
         var CircuitLatitude : Double,
        var CircuitLongitude : Double,
)

class Race (
    var circuit : Circuit,
    var date : String
)






@Entity(tableName = "ticketTable")
class Tickets(
    @PrimaryKey(autoGenerate = true)
    var ticketsId : Int,
    var price : Int,
    var raceId : Race,
    var nameGrandStand : String,
    var nameBlock : String
)