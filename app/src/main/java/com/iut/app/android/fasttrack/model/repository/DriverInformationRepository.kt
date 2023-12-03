package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.dataclass.DriverInformation.DriverInformation
import com.iut.app.android.fasttrack.model.manager.ApiManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object DriverInformationRepository {

    suspend fun getCurrentDriverInformation(id: String): Flow<Response<DriverInformation>> = flow {
        emit(ApiManager.apiService.getDriverById(id))
    }
}