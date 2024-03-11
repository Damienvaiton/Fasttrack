package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TicketsDao {

    @Insert
    fun insertTickets(tickets: Tickets)

    @Query("SELECT * FROM ticketTable WHERE ticketsId = :id")
    fun getTicketsById(id: Int): Tickets

    @Transaction
    @Query("SELECT * FROM ticketTable WHERE userId = :id")
    fun getTicketsByFanId(id: Int): List<Tickets>


}