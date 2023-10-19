package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.manager.ApiManager
import com.iut.app.android.fasttrack.model.schedule.Schedule
import com.iut.app.android.fasttrack.model.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object ScheduleRepository {

    suspend fun getCurrentSeason(): Flow<Response<Schedule>>  = flow {

        val respnse = ApiManager.apiService.getCurrentSeasonFlow()
        respnse.collect {
            emit(it)
        }



    }

    suspend fun getCurrentSeason2(): Flow<Response<Schedule>> = ApiManager.apiService.getCurrentSeasonFlow()


}