package com.iut.app.android.fasttrack.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iut.app.android.fasttrack.model.repository.ScheduleRepository
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response

class ScheduleViewModel : ViewModel() {

    private var _currentSeasonLiveData : MutableLiveData<Response<Schedule>?> = MutableLiveData<Response<Schedule>?>()

    val ScheduleLiveData : LiveData<Response<Schedule>?> = _currentSeasonLiveData

    fun fetchCurrentSeason() {
        viewModelScope.launch {
            ScheduleRepository.getCurrentSeason()
                .catch {
                    Log.e("ScheduleViewModel", "fetchCurrentSeason: ${it.message}")
                }
                .collect {
                    _currentSeasonLiveData.postValue(it)
                }
        }
    }

    fun getnextRace(){
        viewModelScope.launch {
            ScheduleRepository.getNextRace()
                .catch {
                    Log.e("ScheduleViewModel", "fetchCurrentSeason: ${it.message}")
                }
                .collect {
                    _currentSeasonLiveData.postValue(it)
                }
        }
    }

}