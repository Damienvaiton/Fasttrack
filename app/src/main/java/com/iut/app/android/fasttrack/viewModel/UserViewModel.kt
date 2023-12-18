package com.iut.app.android.fasttrack.viewModel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.iut.app.android.fasttrack.model.room.MyDatabase
import com.iut.app.android.fasttrack.model.room.Tickets.TicketsDao
import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import com.iut.app.android.fasttrack.view.Fragements.Profil.Signup

class UserViewModel : ViewModel() {



    var myDatabase: MyDatabase? = MyDatabase.getDatabase()
    var fanDAO: FanDAO? = myDatabase?.getFanDao()
    var ticketDAO: TicketsDao? = myDatabase?.getTicketsDao()




    fun Signup (lastname :String , firstname :String,){

    }

    fun ErrorDialog(reason: String, context: Context) {
        when (reason) {
            "mail" -> {
                AlertDialog.Builder(context)
                    .setTitle("Erreur d'adresse mail")
                    .setMessage("L'adresse mail est déjà utilisée")
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

    fun writeFan(fan: Fan) : String {
        var res = ""
        res += "id : ${fan.id}\n"
        res += "nom : ${fan.name}\n"
        res += "prenom : ${fan.firstName}\n"
        res += "mail : ${fan.mail}\n"
        res += "password : ${fan.password}\n"

        return res
    }


}