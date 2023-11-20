package com.iut.app.android.fasttrack.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.iut.app.android.fasttrack.model.dataclass.schedule.RaceTable
import java.text.DateFormatSymbols

class HomeViewModel : ViewModel() {

    inner class Seance {
        var name : String = ""
        var date : String = ""
        var hour : String = ""
    }

    fun createSeance(name : String, date : String, hour : String): Seance {
        val seance = Seance()
        seance.name = name
        seance.date = date
        seance.hour = hour
        return seance
    }


    fun writeDate(dateFirst: String,dateRace:String): String {
        val month = dateFirst.substring(5,7)
        val daystart = dateFirst.substring(8,10)
        val dayend = dateRace.substring(8,10)
        val monthName: String = DateFormatSymbols().months[month.toInt()-1].subSequence(0,3).toString().uppercase()
        return daystart+ "-"+ dayend + " " + monthName;
    }

    fun getScheduleofTheRace(nextRace : RaceTable?, position : Int): ArrayList<Seance>{
        if (nextRace != null){
            val race = nextRace.races[position]
            // verifions que le champ Sprint existe dans le json nextRace
            val FP1 = createSeance("FP1", race.firstPractice.date,race.firstPractice.time)
            val FP2 = createSeance("FP2", race.secondPractice.date, race.secondPractice.time)
            val Quali = createSeance("Qualification", race.qualifying.date, race.qualifying.time)
            val realrace = createSeance("Race", race.date, race.time)
            if (nextRace.races[0].sprint == null ){
                val FP3 = createSeance("FP3", race.thirdPractice.date, race.thirdPractice.time)
                return arrayListOf<Seance>(FP1,FP2,FP3,Quali,realrace)
            }
            else {
                val sprint = createSeance("Sprint Race", race.sprint.date, race.sprint.time)
            }



        }
        Log.e("Error API getScheduleofTheRace", "No data in the parameter nextRace")
        return arrayListOf<Seance>()
    }

    fun correctNameGP(nameGP : String): String {
        return nameGP.replace("Grand Prix", "GP")
    }

}