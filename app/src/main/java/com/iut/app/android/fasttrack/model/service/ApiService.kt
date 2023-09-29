package com.iut.app.android.fasttrack.model.service

import com.iut.app.android.fasttrack.model.schedule.MRData
import com.iut.app.android.fasttrack.model.schedule.Race
import com.iut.app.android.fasttrack.model.schedule.Schedule
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("current.json")
    suspend fun getCurrentSeason(): Response<Schedule>

    @GET("current.json")

    suspend fun getCurrentSeasonFlow(): Flow<Response<Schedule>>

    @GET("current/{id}.json")
    suspend fun getCurrantRaceByNumber(@Path ("id") raceId : Int): Response<Schedule>






}