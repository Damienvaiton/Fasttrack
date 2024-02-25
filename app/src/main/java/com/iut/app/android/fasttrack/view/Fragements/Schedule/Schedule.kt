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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Schedule.newInstance] factory method to
 * create an instance of this fragment.
 */
class Schedule : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


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

    fun getCorrectFormatDate(date: String): ArrayList<String> {
        val dateList = date.split("-")
        val year = dateList[0]
        val month = dateList[1]
        val day = dateList[2]
        val correctDate = arrayListOf<String>(day, month, year)
        return correctDate
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Schedule.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Schedule().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}