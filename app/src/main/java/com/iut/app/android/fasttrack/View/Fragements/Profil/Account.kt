package com.iut.app.android.fasttrack.View.Fragements.Profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.room.MyDatabase
import com.iut.app.android.fasttrack.model.room.users.FanDAO

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Account : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var myDatabase : MyDatabase? = null
    var fanDAO : FanDAO? = null

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

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val FirstNameTV = view.findViewById<TextView>(R.id.firstNameAccount)
        val LastNameTV = view.findViewById<TextView>(R.id.lastNameAccount)

        FirstNameTV.text = CacheDataSource.FanConnected?.firstName
        LastNameTV.text = CacheDataSource.FanConnected?.name
    }
}