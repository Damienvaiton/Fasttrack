package com.iut.app.android.fasttrack.model.room.Tickets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TicketsDao {

    @Insert
    fun insertTickets(tickets: Tickets)

    @Transaction
    @Query("SELECT * FROM fanTable where id = 1")
    fun getTickets(): List<Tickets>




}