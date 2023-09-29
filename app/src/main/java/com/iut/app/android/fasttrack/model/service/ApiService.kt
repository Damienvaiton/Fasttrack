package com.iut.app.android.fasttrack.model.service

import com.iut.app.android.fasttrack.model.schedule.MRData
import com.iut.app.android.fasttrack.model.schedule.Race
import com.iut.app.android.fasttrack.model.schedule.Schedule
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/api/f1/current.json")
    suspend fun getCurrentSeason(): Response<Schedule>
}