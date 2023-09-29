package com.iut.app.android.fasttrack.model.room

import androidx.room.Query

interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    @Query("SELECT * FROM driver where DriverId = :id")
    fun getDriverById(id: Int): Driver

    @Query("SELECT * FROM fan where FanId = :id")
    fun getFanById(id: Int): Fan

    @Query("SELECT * FROM team where id = :id")
    fun getTeamById(id: Int): Team



}