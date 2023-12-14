package com.iut.app.android.fasttrack.model.room.users

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "fanTable")
class Fan(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var firstName: String,
    var mail: String,
    var password: String,
    var favoriteTeam: String?,
    var favoriteDriver: String?,
    var favoritenumber: Int?

)