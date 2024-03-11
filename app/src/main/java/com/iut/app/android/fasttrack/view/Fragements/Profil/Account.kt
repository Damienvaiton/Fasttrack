package com.iut.app.android.fasttrack.view.Fragements.Profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource


class Account : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val FirstNameTV = view.findViewById<TextView>(R.id.firstNameAccount)
        val myTicketsPage = view.findViewById<TextView>(R.id.myticketbtn)

        FirstNameTV.text = CacheDataSource.FanConnected?.firstName

        myTicketsPage.setOnClickListener {
            val fragment = TicketsPage()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}