package com.iut.app.android.fasttrack.view.Fragements.Schedule

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
class Schedule : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvContacts = view.findViewById(R.id.rvSchedule) as RecyclerView

        val scheduleViewModel by activityViewModels<ScheduleViewModel>()


        scheduleViewModel.ScheduleLiveData.observe(viewLifecycleOwner) { response ->
            val calendar = response?.body()
            scheduleViewModel.ResultsLiveData.observe(viewLifecycleOwner) { response2 ->

                val calendarResult = response2?.body()
                calendar?.let { calend ->
                    calendarResult?.let {
                        val adapter = ScheduleAdapter(calend, it)

                        rvContacts.adapter = adapter

                        rvContacts.layoutManager = LinearLayoutManager(this.context)

                        adapter.detailCircuitLD.observe(viewLifecycleOwner) { circuit ->
                            scheduleViewModel.setDetailCircuit(circuit)
                            val fragment = DetailedCircuit()
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.replace(R.id.frame_layout, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()

                        }


                    }
                }
            }


        }

        scheduleViewModel.fetchCurrentSeason()
        scheduleViewModel.fetchRaceResults()


    }
}