package com.iut.app.android.fasttrack.model.room.users

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
open class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name : String? = ""
    var firstName : String? = ""

    constructor(id:Int,name: String, firstName: String) {
        this.id = id
        this.name = name
        this.firstName = firstName
    }



}


@Entity(tableName = "fanTable")
class Fan : User {
    var mail : String = ""
    var password : String = ""

    constructor(id:Int,name: String, firstName: String, mail: String, password: String) : super(id,name, firstName) {
        this.mail = mail
        this.password = password
    }

}