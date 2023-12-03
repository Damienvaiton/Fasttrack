package com.iut.app.android.fasttrack.utils.converters

import android.util.Log
import androidx.room.TypeConverter
import com.iut.app.android.fasttrack.model.room.Tickets.Circuit
import com.iut.app.android.fasttrack.model.room.Tickets.Race

class Converters {
    @TypeConverter
    fun fromCircuit(value: Circuit): String {
        val circuitId = value.CircuitId
        val circuitName = value.CircuitName
        val circuitLocation = value.CircuitLocation
        val circuitCountry = value.CircuitCountry
        val circuitLatitude = value.CircuitLatitude
        val circuitLongitude = value.CircuitLongitude
        return "$circuitId,$circuitName,$circuitLocation,$circuitCountry,$circuitLatitude,$circuitLongitude"
    }

    @TypeConverter
    fun toCircuit(value: String): Circuit {
        val parts = value.split(",")
        return Circuit(
            CircuitId = parts[0].toInt(),
            CircuitName = parts[1],
            CircuitLocation = parts[2],
            CircuitCountry = parts[3],
            CircuitLatitude = parts[4].toDouble(),
            CircuitLongitude = parts[5].toDouble()
        )
    }

    @TypeConverter
    fun fromRace(values: Race): String {
        val circuit = fromCircuit(values.circuit)
        val date = values.date
        return "$circuit,$date"
    }

    @TypeConverter
    fun toRace(value: String): Race {
        val parts = value.split(",")
        var datpass = ""
        for (i in 0 until parts.size - 1) {
            datpass += parts[i] + ","
        }
        val circuit = toCircuit(datpass)
        val date = parts[6]
        return Race(circuit, date)
    }

}