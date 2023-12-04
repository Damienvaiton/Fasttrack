package com.iut.app.android.fasttrack.model.service

import com.iut.app.android.fasttrack.model.dataclass.DriverInformation.DriverInformation
import com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking.ConstructorRanking
import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.DriverRanking
import com.iut.app.android.fasttrack.model.dataclass.schedule.Results.ResultsStart
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("current.json")
    suspend fun getCurrentSeasonFlow(): Response<Schedule>

    @GET("current/next.json")
    suspend fun getNextRace(): Response<Schedule>

    @GET("current/{id}.json")
    suspend fun getCurrantRaceByNumber(@Path("id") raceId: Int): Response<Schedule>


    @GET("current/driverStandings.json")
    suspend fun getCurrentDriverRanking(): Response<DriverRanking>

    @GET("current/constructorStandings.json")
    suspend fun getCurrentConstructorRanking(): Response<ConstructorRanking>

    @GET("/drivers/{id}.json")
    suspend fun getDriverById(@Path("id") driverId: String): Response<DriverInformation>


    //mettre en place un systeme de pagination avec limit = 30
    @GET("current/results.json")
    suspend fun getCurrentRaceResults(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ResultsStart>


}