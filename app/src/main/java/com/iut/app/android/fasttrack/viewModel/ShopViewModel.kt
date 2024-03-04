package com.iut.app.android.fasttrack.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iut.app.android.fasttrack.model.dataclass.schedule.Race
import com.iut.app.android.fasttrack.model.repository.ShopRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ShopViewModel : ViewModel() {

    private var _selectedRaceLiveData: MutableLiveData<Race?> = MutableLiveData<Race?>()

    val selectedRaceLiveData: LiveData<Race?> = _selectedRaceLiveData

    fun getSelectedRace() {
        viewModelScope.launch {
            ShopRepository.getSelectedRace().collect {
                if (it != null) {
                    _selectedRaceLiveData.postValue(it)
                }else {
                    Timber.tag("ShopViewModel").e("Selected Race null")
                }
            }
        }
    }

    fun setSelectedRace(race : Race) {
        viewModelScope.launch {
            ShopRepository.setSelectedRace(race).collect {
                if (it) {
                    Timber.tag("ShopViewModel").d("setSelectedRace success")
                } else {
                    Timber.tag("ShopViewModel").e("setSelectedRace failed")
                }
            }
        }
    }





}

