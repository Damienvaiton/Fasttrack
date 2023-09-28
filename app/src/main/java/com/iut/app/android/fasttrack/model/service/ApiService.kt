package com.iut.app.android.fasttrack.model.service

import com.iut.app.android.fasttrack.model.schedule.Race
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("current")
    suspend fun getCurrentSeason(): Response <List<Race>>
}