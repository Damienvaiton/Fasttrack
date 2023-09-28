package com.iut.app.android.fasttrack.model.service

import com.iut.app.android.fasttrack.model.SeasonScheduleCallBack
import retrofit2.http.GET

interface ScheduleService {

    @GET("current")
    suspend fun getCurrentSeasonSchedule(): SeasonScheduleCallBack

}