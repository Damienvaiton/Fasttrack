package com.iut.app.android.fasttrack.viewModel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
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

}