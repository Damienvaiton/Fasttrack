package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.dataclass.schedule.Circuit
import com.iut.app.android.fasttrack.model.dataclass.schedule.Results.ResultsStart
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import com.iut.app.android.fasttrack.model.manager.ApiManager
import kotlinx.coroutines.flow.Flow
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
        var limit = 80
        val remainingItems = nb_page * 20


        while (limit <= remainingItems) {
            limit += 80
            emit(ApiManager.apiService.getCurrentRaceResults(offset, limit))
        }
    }

    fun setDetailCircuit(circuit : Circuit): Flow<Boolean> = flow {
        emit(CacheDataSource.setCircuit(circuit))
    }

    fun getDetailCircuit(): Flow<Circuit?> = flow {
        emit(CacheDataSource.getCircuit())
    }

}
