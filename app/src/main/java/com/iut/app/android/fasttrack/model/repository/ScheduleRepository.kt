package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.dataclass.schedule.Results.ResultsStart
import com.iut.app.android.fasttrack.model.manager.ApiManager
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import com.iut.app.android.fasttrack.model.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object ScheduleRepository {

    suspend fun getCurrentSeason(): Flow<Response<Schedule>> = flow {
        emit(ApiManager.apiService.getCurrentSeasonFlow())
    }

    suspend fun getNextRace(): Flow<Response<Schedule>> = flow {
        emit(ApiManager.apiService.getNextRace())
    }

    suspend fun getRaceResults(nb_page: Int): Flow<Response<ResultsStart>> = flow {

        var offset = 0
        var limit = 30
        var remainingItems = nb_page


        while (remainingItems > 0) {
            emit(ApiManager.apiService.getCurrentRaceResults(offset, limit))
            offset += limit
            remainingItems--
        }
    }


}