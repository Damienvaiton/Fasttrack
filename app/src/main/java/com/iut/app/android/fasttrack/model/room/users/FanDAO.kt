package com.iut.app.android.fasttrack.model.room.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FanDAO {

    @Insert
    fun insertFan(fan: Fan)

    @Query("SELECT EXISTS(SELECT * FROM fanTable WHERE mail = :mail)")
    fun isFan(mail: String): Boolean

    @Query("SELECT * FROM fanTable WHERE mail = :mail AND password = :password")
    fun login(mail: String, password: String): Boolean


}
