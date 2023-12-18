package com.iut.app.android.fasttrack.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.dataclass.schedule.Circuit
import com.iut.app.android.fasttrack.model.dataclass.schedule.Race
import com.iut.app.android.fasttrack.model.dataclass.schedule.Results.ResultsStart
import com.iut.app.android.fasttrack.model.repository.ScheduleRepository
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class ScheduleViewModel : ViewModel() {

    private var _currentSeasonLiveData: MutableLiveData<Response<Schedule>?> =
        MutableLiveData<Response<Schedule>?>()
    private var _currentNextRaceLiveData: MutableLiveData<Response<Schedule>?> =
        MutableLiveData<Response<Schedule>?>()
    private var _currentResultsLiveData: MutableLiveData<Response<ResultsStart>?> =
        MutableLiveData<Response<ResultsStart>?>()

    val ScheduleLiveData: LiveData<Response<Schedule>?> = _currentSeasonLiveData
    val NextRaceLiveData: LiveData<Response<Schedule>?> = _currentNextRaceLiveData
    val ResultsLiveData: LiveData<Response<ResultsStart>?> = _currentResultsLiveData

    fun fetchCurrentSeason() {
        viewModelScope.launch {
            ScheduleRepository.getCurrentSeason()
                .catch {
                    Timber.tag("ScheduleViewModel").e("fetchCurrentSeason: %s", it.message)
                }
                .collect {
                    _currentSeasonLiveData.postValue(it)
                }
        }
    }

    fun getnextRace() {
        viewModelScope.launch {
            ScheduleRepository.getNextRace()
                .catch {
                    Timber.tag("ScheduleViewModel").e("fetchCurrentSeason: %s", it.message)
                }
                .collect {
                    _currentNextRaceLiveData.postValue(it)
                }
        }
    }

    fun fetchRaceResults() {
        viewModelScope.launch {
            ScheduleRepository.getRaceResults(24)
                .catch {
                    Timber.tag("ScheduleViewModel").e("fetchCurrentSeason: %s", it.message)
                }
                .collect {
                    _currentResultsLiveData.postValue(it)
                }
        }
    }




}