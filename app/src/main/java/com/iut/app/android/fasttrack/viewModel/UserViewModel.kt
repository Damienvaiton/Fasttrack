package com.iut.app.android.fasttrack.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.enums.FanErrors
import com.iut.app.android.fasttrack.model.repository.UserRepository
import com.iut.app.android.fasttrack.model.response.FanResponse
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.iut.app.android.fasttrack.model.room.users.Fan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel : ViewModel() {

    val userRepo = UserRepository


    private var _signupResponseLD = MutableLiveData<FanResponse>()
    private var _loginResponseLD = MutableLiveData<FanResponse>()
    private var _ticketResponse = MutableLiveData<Boolean>()
    private var _ticketsResponseLD = MutableLiveData<List<Tickets>>()


    val signupResponseLD: MutableLiveData<FanResponse> = _signupResponseLD
    val loginResponseLD: MutableLiveData<FanResponse> = _loginResponseLD
    val ticketsResponse : MutableLiveData<Boolean> = _ticketResponse
    val ticketsResponseLD: MutableLiveData<List<Tickets>> = _ticketsResponseLD


    @SuppressLint("UseRequireInsteadOfGet")
    fun Signup(
        name: String,
        firstName: String,
        mail: String,
        password: String,
        favoriteTeam: String?,
        favoriteDriver: String?,
        favoritenumber: Int?
    ) {
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
                    userRepo.insertFan(fan).collect {
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

    fun Login(mail: String, password: String) {
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

    fun insertTicket(
        price: Int,
        userId: Int,
        raceId: String,
        nameGrandStand: String,
        nameBlock: String,
        namePlace: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val ticket = Tickets(
                0,
                price,
                userId,
                raceId,
                nameGrandStand,
                nameBlock,
                namePlace
            )
            userRepo.insertTicket(ticket).collect {
                ticketsResponse.postValue(it)
            }
        }
    }

    fun isUserConnected(): Boolean {
        return userRepo.isUserConnected()
    }

    fun getFanConnected(): Fan? {
        return userRepo.getFanConnected()
    }

     fun getTicketsByUserId(userId: Int) {
         CoroutineScope(Dispatchers.IO).launch {
            userRepo.getTicketsByFanId(userId).collect {
                Timber.e("UserViewModel get tickets : $it")
                _ticketsResponseLD.postValue(it)
            }
        }
    }




}