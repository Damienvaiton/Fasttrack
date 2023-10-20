package com.iut.app.android.fasttrack.model.service

import com.iut.app.android.fasttrack.model.Rankings.ConstructorRanking.ConstructorRanking
import com.iut.app.android.fasttrack.model.Rankings.DriverRanking.DriverRanking
import com.iut.app.android.fasttrack.model.schedule.Schedule
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("current.json")
    suspend fun getCurrentSeasonFlow(): Response<Schedule>

    @GET("current/next.json")
    suspend fun getNextRace(): Response<Schedule>

    @GET("current/{id}.json")
    suspend fun getCurrantRaceByNumber(@Path ("id") raceId : Int): Response<Schedule>


    @GET("current/driverStandings.json")
    suspend fun getCurrentDriverRanking(): Response<DriverRanking>

    @GET("current/constructorStandings.json")
    suspend fun getCurrentConstructorRanking(): Response<ConstructorRanking>






}