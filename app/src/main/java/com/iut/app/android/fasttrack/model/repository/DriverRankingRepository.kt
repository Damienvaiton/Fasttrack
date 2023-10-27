package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.DriverRanking
import com.iut.app.android.fasttrack.model.manager.ApiManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object DriverRankingRepository {
    suspend fun getCurrentDriverRanking(): Flow<Response<DriverRanking>>  = flow {
        emit(ApiManager.apiService.getCurrentDriverRanking())
    }
}