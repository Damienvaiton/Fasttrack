package com.iut.app.android.fasttrack.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking.ConstructorRanking
import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.DriverRanking
import com.iut.app.android.fasttrack.model.repository.ConstructorRankingRepository
import com.iut.app.android.fasttrack.model.repository.DriverRankingRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RankingViewModel : ViewModel() {

    private var _driverRankingLiveData = MutableLiveData<DriverRanking>()
    private var _constructorRankingLiveData = MutableLiveData<ConstructorRanking>()

    val driverLiveDataRanking: MutableLiveData<DriverRanking> = _driverRankingLiveData
    val constructorLiveDataRanking: MutableLiveData<ConstructorRanking> = _constructorRankingLiveData

    fun fetchDriverRanking() {
        viewModelScope.launch {
            DriverRankingRepository.getCurrentDriverRanking()
                .catch {
                    Log.e("RankingViewModel", "fetchDriverRanking: ${it.message}")

                }
                .collect {
                    _driverRankingLiveData.postValue(it.body())
                }
        }
    }

    fun fetchConstructorRanking() {
        viewModelScope.launch {
           ConstructorRankingRepository.getCurrentConstructorRanking()
                .catch {
                    Log.e("RankingViewModel", "fetchConstructorRanking: ${it.message}")

                }
                .collect {
                    _constructorRankingLiveData.postValue(it.body())
                }
        }
    }




}