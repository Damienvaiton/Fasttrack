package com.iut.app.android.fasttrack.viewModel

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.enums.FanErrors
import com.iut.app.android.fasttrack.model.repository.UserRepository
import com.iut.app.android.fasttrack.model.response.FanResponse
import com.iut.app.android.fasttrack.model.room.MyDatabase
import com.iut.app.android.fasttrack.model.room.Tickets.TicketsDao
import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {


    var myDatabase: MyDatabase? = MyDatabase.getDatabase()
    var fanDAO: FanDAO? = myDatabase?.getFanDao()
    val userRepo = UserRepository
    var ticketDAO: TicketsDao? = myDatabase?.getTicketsDao()

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
            if (fanDAO!!.isFan(mail)) {
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
                    if (fanDAO!!.login(mail, password)) {
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







       /* var resStatus = false
        CoroutineScope(Dispatchers.IO).launch {
            if (mail == "" || password == "") {
                Signup().context?.let { ErrorDialog("void", it) }
            } else {
                if (fanDAO!!.isFan(mail)) {
                    if (fanDAO!!.login(mail, password)) {
                        CacheDataSource.setConnected(true)
                        CacheDataSource.setFanConnected(fanDAO!!.getFanByMail(mail))
                        Timber.tag("Login").d("Login with success")
                        resStatus = true
                    } else {
                        Signup().context?.let { ErrorDialog("password", it) }
                    }
                } else {
                    Signup().context?.let { ErrorDialog("mail2", it) }
                }
            }
        }
        return resStatus*/
    }

    fun ErrorDialog(reason: String, context: Context) {
        when (reason) {
            "mail" -> {

            }

            "mail2" -> {
                AlertDialog.Builder(context)
                    .setTitle("Erreur d'adresse mail")
                    .setMessage("L'adresse mail n'existe pas")
                    .setPositiveButton("OK", null)
                    .show()
            }

            "password" -> {
                AlertDialog.Builder(context)
                    .setTitle("Erreur de mot de passe")
                    .setMessage("Mot de passe incorrect")
                    .setPositiveButton("OK", null)
                    .show()
            }

            "void" -> {
                AlertDialog.Builder(context)
                    .setTitle("Erreur de saisie")
                    .setMessage("Veuillez remplir tous les champs")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

    }

    fun writeFan(fan: Fan): String {
        var res = ""
        res += "id : ${fan.id}\n"
        res += "nom : ${fan.name}\n"
        res += "prenom : ${fan.firstName}\n"
        res += "mail : ${fan.mail}\n"
        res += "password : ${fan.password}\n"

        return res
    }


}