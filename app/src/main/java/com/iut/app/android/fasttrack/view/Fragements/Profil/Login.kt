package com.iut.app.android.fasttrack.view.Fragements.Profil

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.room.MyDatabase
import com.iut.app.android.fasttrack.model.room.Tickets.Circuit
import com.iut.app.android.fasttrack.model.room.Tickets.Race
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.iut.app.android.fasttrack.model.room.Tickets.TicketsDao
import com.iut.app.android.fasttrack.model.room.users.FanDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    //Database
    var myDatabase : MyDatabase? = null
    var fanDAO : FanDAO? = null
    var ticketDAO : TicketsDao? = null






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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.connexion_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.e("OnViewCreated", "Passage dans account")



        val signupbtn = view.findViewById<Button>(R.id.signupbtn)

        val loginbtn = view.findViewById<Button>(R.id.submit)

        signupbtn.setOnClickListener {
            val fragment = Signup()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        loginbtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
            val usernameTV = view.findViewById<TextView>(R.id.username)
            val passwordTV = view.findViewById<TextView>(R.id.password)

            myDatabase = MyDatabase.getDatabase()
            fanDAO = myDatabase!!.getFanDao()
            ticketDAO = myDatabase!!.getTicketsDao()

            Log.e(
                "Connected",
                fanDAO!!.login(usernameTV.text.toString(), passwordTV.text.toString()).toString()
            )

                if (fanDAO!!.login(usernameTV.text.toString(), passwordTV.text.toString())) {
                    Log.e("Connected", "Connected Good")
                    CacheDataSource.setConnected(true)
                    Log.e("Connected status", CacheDataSource.connected.toString())
                    if (CacheDataSource.setFanConnected(fanDAO!!.getFanByMail(usernameTV.text.toString()))) {
                        val fragment = Account()
                        val transaction =
                            requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.frame_layout, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                } else {
                    Log.e("Connected", "Connected Bad")
                    var circuit = Circuit(0, "Circuit Paul Ricard", "Castellet", "France", 43.2506, 5.79167)
                    var Race = Race(circuit, "test")
                    var tickettest = Tickets(0, 12,1, Race, "Tribune Pierre Gasly", "Bloc D")
                    ticketDAO!!.insertTickets(tickettest)
                    ticketDAO!!.insertTickets(tickettest)


                }

            }
        }




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Account.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}