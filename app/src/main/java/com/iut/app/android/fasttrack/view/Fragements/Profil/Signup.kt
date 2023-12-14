package com.iut.app.android.fasttrack.view.Fragements.Profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.room.MyDatabase
import com.iut.app.android.fasttrack.model.room.users.Fan
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import com.iut.app.android.fasttrack.viewModel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Signup : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var connected: Boolean? = false


    //Database
    var myDatabase: MyDatabase? = null
    var fanDAO: FanDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.inscription_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Database

        myDatabase = MyDatabase.getDatabase()
        fanDAO = myDatabase!!.getFanDao()


        //Find elements
        val firstname = view.findViewById<TextView>(R.id.firstname)
        val lastname = view.findViewById<TextView>(R.id.lastname)
        val usernameTV = view.findViewById<TextView>(R.id.username)
        val passwordTV = view.findViewById<TextView>(R.id.password)
        val buttonEnregistrer = view.findViewById<Button>(R.id.submit)


        buttonEnregistrer.setOnClickListener {


            CoroutineScope(Dispatchers.IO).launch {

                if (fanDAO!!.isFan(usernameTV.text.toString())) {
                    connected = false
                    withContext(Dispatchers.Main) {
                        UserViewModel().ErrorDialog("mail", requireContext())
                    }
                } else {
                    if (usernameTV.text.toString() != "" && passwordTV.text.toString() != "" && firstname.text.toString() != "" && lastname.text.toString() != "") {
                        val fan = Fan(
                            0,
                            lastname.text.toString(),
                            firstname.text.toString(),
                            usernameTV.text.toString(),
                            passwordTV.text.toString(),
                            null,
                            null,
                            44
                        )
                        fanDAO!!.insertFan(fan)
                        connected = true
                    } else {
                        connected = false
                    }


                    val fragment = Login()
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame_layout, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }
    }
}
