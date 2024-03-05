package com.iut.app.android.fasttrack.viewModel

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.enums.FanErrors
import com.iut.app.android.fasttrack.model.repository.UserRepository
import com.iut.app.android.fasttrack.model.response.FanResponse
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.iut.app.android.fasttrack.model.room.users.Fan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val userRepo = UserRepository


    private var _signupResponseLD = MutableLiveData<FanResponse>()
    private var _loginResponseLD = MutableLiveData<FanResponse>()

    val signupResponseLD: MutableLiveData<FanResponse> = _signupResponseLD
    val loginResponseLD: MutableLiveData<FanResponse> = _loginResponseLD


    @SuppressLint("UseRequireInsteadOfGet")
    fun Signup(
        name: String,
        firstName: String,
        mail: String,
        password: String,
        favoriteTeam: String?,
        favoriteDriver: String?,
        favoritenumber: Int?
    ){
        var fanResponse = FanResponse()
        CoroutineScope(Dispatchers.IO).launch {
            if (userRepo.isFan(mail)) {
                fanResponse.addFanError(FanErrors.MAIL_ALREADY_EXISTS)
            } else {
                if (mail != "" && password != "" && firstName != "" && name != "") {
                    val fan = Fan(
                        0,
                        name,
                        firstName,
                        mail,
                        password,
                        favoriteTeam,
                        favoriteDriver,
                        favoritenumber
                    )
                    userRepo.insertFan(fan).collect{
                        if (it) {
                            fanResponse.setFan(fan)
                        } else {
                            fanResponse.addFanError(FanErrors.MAIL_ALREADY_EXISTS)
                        }
                        _signupResponseLD.postValue(fanResponse)
                    }

                }
            }

        }
    }

    fun Login(mail: String, password: String){
        var fanResponse = FanResponse()
        CoroutineScope(Dispatchers.IO).launch {
            if (mail == "" || password == "") {
                fanResponse.addFanError(FanErrors.VOID)
            } else {
                if (userRepo.isFan(mail)) {
                    if (userRepo.login(mail, password)) {
                        CacheDataSource.setConnected(true)
                        CacheDataSource.setFanConnected(userRepo.getFanByMail(mail))
                        fanResponse.setFan(userRepo.getFanByMail(mail))
                    } else {
                        fanResponse.addFanError(FanErrors.PASSWORD_INCORRECT)
                    }
                } else {
                    fanResponse.addFanError(FanErrors.MAIL_NOT_FOUND)
                }
            }
            _loginResponseLD.postValue(fanResponse)
        }


    }

    suspend fun insertTicket(ticket: Tickets) {
        viewModelScope.launch {
            userRepo.insertTicket(ticket).collect{
                if (it) {
                    println("Ticket inserted")
                } else {
                    AlertDialog.Builder(null)
                        .setTitle("Error")
                        .setMessage("An error occured while inserting the ticket")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
        }
    }


}