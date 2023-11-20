package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.jar.Attributes.Name


class Circuit {
     var CircuitId : Int = 0
        var CircuitName : String = ""
        var CircuitLocation : String = ""
        var CircuitCountry : String = ""
     var CircuitLatitude : Double = 0.0
        var CircuitLongitude : Double = 0.0

    constructor(CircuitId: Int, CircuitName: String, CircuitLocation: String, CircuitCountry: String, CircuitLatitude: Double, CircuitLongitude: Double) {
        this.CircuitId = CircuitId
        this.CircuitName = CircuitName
        this.CircuitLocation = CircuitLocation
        this.CircuitCountry = CircuitCountry
        this.CircuitLatitude = CircuitLatitude
        this.CircuitLongitude = CircuitLongitude
    }
}

class Race {
    var circuitId : Int = 0
    var date : String = ""

    constructor(circuitId: Int, date: String) {
        this.circuitId = circuitId
        this.date = date
    }
}






@Entity(tableName = "ticketTable")
class Tickets{
    @PrimaryKey(autoGenerate = true)
    var ticketsId : Int = 0
    var price : Int = 0
    var FanId : Int = 0
    var raceId : Int = 0
    var nameGrandStand : String = ""
    var nameBlock : String = ""

    constructor(ticketsId: Int, price: Int, FanId: Int, raceId: Int, nameGrandStand: String, nameBlock: String) {
        this.ticketsId = ticketsId
        this.price = price
        this.FanId = FanId
        this.raceId = raceId
        this.nameGrandStand = nameGrandStand
        this.nameBlock = nameBlock
    }

}