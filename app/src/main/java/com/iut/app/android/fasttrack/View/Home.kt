package com.iut.app.android.fasttrack.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.gson.JsonObject
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.schedule.RaceTable
import com.iut.app.android.fasttrack.model.dataclass.schedule.Sprint
import com.iut.app.android.fasttrack.viewModel.HomeViewModel
import com.iut.app.android.fasttrack.viewModel.ScheduleViewModel
import java.text.DateFormatSymbols
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Elements de la page d'accueil
        val card = view.findViewById<ImageView>(R.id.divSmallNextGp)
        val card2 = view.findViewById<ImageView>(R.id.divNextGpInfos)
        val countryGp = view.findViewById<TextView>(R.id.firstNameNextGp)
        val nameGp = view.findViewById<TextView>(R.id.secondNameNextGp)
        val dateGp = view.findViewById<TextView>(R.id.dateNextGP)
        val trackGp = view.findViewById<ImageView>(R.id.imageTrackNextGp)


        val scheduleViewModel by activityViewModels<ScheduleViewModel>()
        val homeViewModel by activityViewModels<HomeViewModel>()


        scheduleViewModel.getnextRace()

        //aller chercher un attribut dans le retour d api scheduleViewModel.getnextRace() et l'afficher dans le textview countryGp

        scheduleViewModel.ScheduleLiveData.observe(viewLifecycleOwner){response ->
            val nextRace = response?.body()

            if (nextRace != null) {
                val idCircuit = nextRace.mRData.raceTable.races[0].circuit.circuitId
                val resources = view.context.resources
                val resourceId =
                    resources.getIdentifier(idCircuit, "color", view.context.packageName)
                val resourceID2 =
                    resources.getIdentifier(idCircuit + "2", "color", view.context.packageName)
                card.setColorFilter(resources.getColor(resourceId, null))
                Log.e("color", idCircuit)
                card2.setColorFilter(resources.getColor(resourceID2, null))

                val Schedule = homeViewModel.getScheduleofTheRace(nextRace.mRData.raceTable,0)
            }


            if (nextRace != null) {
                countryGp.setText(nextRace.mRData.raceTable.races[0].circuit.location.country)
            }
            if (nextRace != null) {
                nameGp.setText(homeViewModel.correctNameGP(nextRace.mRData.raceTable.races[0].raceName))
            }
            if (nextRace != null) {
                val correctDate = homeViewModel.writeDate(nextRace.mRData.raceTable.races[0].firstPractice.date,nextRace.mRData.raceTable.races[0].date)
                dateGp.setText(correctDate)
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
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}