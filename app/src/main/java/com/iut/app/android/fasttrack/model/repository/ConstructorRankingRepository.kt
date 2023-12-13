package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking.ConstructorRanking
import com.iut.app.android.fasttrack.model.manager.ApiManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

object ConstructorRankingRepository {
    suspend fun getCurrentConstructorRanking(): Flow<Response<ConstructorRanking>> = flow {
        emit(ApiManager.apiService.getCurrentConstructorRanking())
        Timber.tag("GetConsRanking").e(
            "getCurrentConstructorRanking: " + ApiManager.apiService.getCurrentConstructorRanking()
                .body()
        )
    }
}