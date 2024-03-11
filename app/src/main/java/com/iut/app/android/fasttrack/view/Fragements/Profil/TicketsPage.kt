package com.iut.app.android.fasttrack.view.Fragements.Profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.viewModel.ScheduleViewModel
import com.iut.app.android.fasttrack.viewModel.UserViewModel

class TicketsPage : Fragment() {

    val userVM by activityViewModels<UserViewModel>()
    val scheduleViewModel by activityViewModels<ScheduleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_tickets_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val  rvTickets = view.findViewById(R.id.rvMyTickets) as RecyclerView

        userVM.ticketsResponseLD.observe(viewLifecycleOwner) { response ->
            scheduleViewModel.ScheduleLiveData.observe(viewLifecycleOwner) { response2 ->
                val sch = response2?.body()
                val adapter = TicketsPageAdapter(response,sch)

                rvTickets.adapter = adapter

                rvTickets.layoutManager = LinearLayoutManager(this.context)

            }
        }

        userVM.getTicketsByUserId(userVM.getFanConnected()!!.id)
    }
}