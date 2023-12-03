package com.iut.app.android.fasttrack.model.room

import androidx.room.Embedded
import androidx.room.Relation
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.iut.app.android.fasttrack.model.room.users.Fan

data class FanWithTickets(
    @Embedded val user: Fan,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val tickets: List<Tickets>
)