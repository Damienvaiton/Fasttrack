package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.iut.app.android.fasttrack.model.room.FanWithTickets

@Dao
interface TicketsDao {

    @Insert
    fun insertTickets(tickets: Tickets)

    @Query("SELECT * FROM ticketTable WHERE ticketsId = :id")
    fun getTicketsById(id: Int): Tickets

    //J'ai pas réussi à faire la requête pour récupérer les tickets d'un fan
    @Transaction
    @Query("SELECT * FROM fanTable")
    fun getTicketsByFanId(): List<FanWithTickets>




}