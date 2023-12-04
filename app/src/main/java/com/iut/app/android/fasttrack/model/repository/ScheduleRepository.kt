package com.iut.app.android.fasttrack.model.repository

import android.util.Log
import com.iut.app.android.fasttrack.model.dataclass.schedule.Results.ResultsStart
import com.iut.app.android.fasttrack.model.manager.ApiManager
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import com.iut.app.android.fasttrack.model.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

object ScheduleRepository {

    suspend fun getCurrentSeason(): Flow<Response<Schedule>> = flow {
        emit(ApiManager.apiService.getCurrentSeasonFlow())
    }

    suspend fun getNextRace(): Flow<Response<Schedule>> = flow {
        emit(ApiManager.apiService.getNextRace())
    }

    suspend fun getRaceResults(nb_page: Int): Flow<Response<ResultsStart>> = flow {

        var offset = 0
        var limit = 80
        val remainingItems = nb_page * 20


        while (limit <= remainingItems) {
            limit += 80
            emit(ApiManager.apiService.getCurrentRaceResults(offset, limit))
        }


    }
}


