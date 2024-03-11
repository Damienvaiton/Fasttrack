package com.iut.app.android.fasttrack.model.repository

import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.dataclass.schedule.Race
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object ShopRepository {

    suspend fun getSelectedRace() : Flow<Race?> = flow {
        emit(CacheDataSource.getRaceSelected())
    }

    suspend fun setSelectedRace(race: Race) : Flow<Boolean> = flow {
        emit(CacheDataSource.setRaceSelected(race))
    }


}