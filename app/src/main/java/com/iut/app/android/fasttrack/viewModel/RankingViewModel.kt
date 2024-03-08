package com.iut.app.android.fasttrack.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking.ConstructorRanking
import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.DriverRanking
import com.iut.app.android.fasttrack.model.repository.ConstructorRankingRepository
import com.iut.app.android.fasttrack.model.repository.DriverInformationRepository
import com.iut.app.android.fasttrack.model.repository.DriverRankingRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

class RankingViewModel : ViewModel() {

    private var _driverRankingLiveData = MutableLiveData<DriverRanking>()
    private var _constructorRankingLiveData = MutableLiveData<ConstructorRanking>()
    private var _driverInformationLiveData = MutableLiveData<com.iut.app.android.fasttrack.model.dataclass.DriverInformation.DriverInformation>()
    private var _flagLiveData = MutableLiveData<okhttp3.ResponseBody>()

    val driverLiveDataRanking: MutableLiveData<DriverRanking> = _driverRankingLiveData
    val constructorLiveDataRanking: MutableLiveData<ConstructorRanking> = _constructorRankingLiveData
    val driverInformationLiveData: MutableLiveData<com.iut.app.android.fasttrack.model.dataclass.DriverInformation.DriverInformation> = _driverInformationLiveData
    val flagLiveData: MutableLiveData<okhttp3.ResponseBody> = _flagLiveData

    fun fetchDriverRanking() {
        viewModelScope.launch {
            DriverRankingRepository.getCurrentDriverRanking()
                .catch {
                    Timber.tag("RankingViewModel").e("fetchDriverRanking: %s", it.message)
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
                    Timber.tag("RankingViewModel").e("fetchConstructorRanking: %s", it.message)

                }
                .collect {
                    _constructorRankingLiveData.postValue(it.body())
                }
        }
    }

    fun getDriverInformation(id: String) {
        viewModelScope.launch {
            DriverInformationRepository.getCurrentDriverInformation(id)
                .catch {
                    Timber.tag("RankingViewModel").e("fetchDriverRanking: %s", it.message)
                }
                .collect {
                    _driverInformationLiveData.postValue(it.body())
                }
        }
    }




}