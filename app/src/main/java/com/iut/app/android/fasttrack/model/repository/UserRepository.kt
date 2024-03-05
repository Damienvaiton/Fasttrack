package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.room.MyDatabase
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.iut.app.android.fasttrack.model.room.Tickets.TicketsDao
import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserRepository {

    var myDatabase: MyDatabase? = MyDatabase.getDatabase()
    var fanDAO: FanDAO? = myDatabase?.getFanDao()
    var ticketDAO: TicketsDao? = myDatabase?.getTicketsDao()
    fun insertFan(user: Fan): Flow<Boolean> = flow {
        try {
            fanDAO!!.insertFan(user)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun isFan(mail: String): Boolean {
        return fanDAO!!.isFan(mail)
    }

    fun login(mail: String, password: String): Boolean {
        return fanDAO!!.login(mail, password)
    }

    fun getFanByMail(mail: String): Fan {
        return fanDAO!!.getFanByMail(mail)
    }

    fun insertTicket(ticket: Tickets): Flow<Boolean> = flow {
        try {
            ticketDAO!!.insertTickets(ticket)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

}
