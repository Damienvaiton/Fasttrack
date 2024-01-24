package com.iut.app.android.fasttrack.model.enums

enum class FanErrors (val message: String){
    MAIL_ALREADY_EXISTS("L'adresse mail est déjà utilisée"),
    MAIL_NOT_FOUND("L'adresse mail n'existe pas"),
    PASSWORD_INCORRECT("Le mot de passe est incorrect"),
    PASSWORD_NOT_MATCH("Les mots de passe ne correspondent pas"),
    VOID("Veuillez remplir tous les champs"),
}