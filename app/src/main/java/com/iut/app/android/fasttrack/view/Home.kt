package com.iut.app.android.fasttrack.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.viewModel.HomeViewModel
import com.iut.app.android.fasttrack.viewModel.ScheduleViewModel
import timber.log.Timber

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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Elements de la page d'accueil
        val card = view.findViewById<ImageView>(R.id.divSmallNextGp)
        val card2 = view.findViewById<ImageView>(R.id.divNextGpInfos)
        val countryGp = view.findViewById<TextView>(R.id.firstNameNextGp)
        val nameGp = view.findViewById<TextView>(R.id.secondNameNextGp)
        val dateGp = view.findViewById<TextView>(R.id.dateNextGP)
        val trackGp = view.findViewById<ImageView>(R.id.imageTrackNextGp)


        //Planning de la next race
        val nomSchedule1 = view.findViewById<TextView>(R.id.seance1nextgp)
        val timeSchedule1 = view.findViewById<TextView>(R.id.time1nextgp)
        val dateSchedule1 = view.findViewById<TextView>(R.id.date1nextgp)

        val nomSchedule2 = view.findViewById<TextView>(R.id.seance2nextgp)
        val timeSchedule2 = view.findViewById<TextView>(R.id.time2nextgp)
        val dateSchedule2 = view.findViewById<TextView>(R.id.date2nextgp)

        val nomSchedule3 = view.findViewById<TextView>(R.id.seance3nextgp)
        val timeSchedule3 = view.findViewById<TextView>(R.id.time3nextgp)
        val dateSchedule3 = view.findViewById<TextView>(R.id.date3nextgp)


        val scheduleViewModel by activityViewModels<ScheduleViewModel>()
        val homeViewModel by activityViewModels<HomeViewModel>()


        scheduleViewModel.getnextRace()

        var ScheduleY = ArrayList<HomeViewModel.Seance>()

        //aller chercher un attribut dans le retour d api scheduleViewModel.getnextRace() et l'afficher dans le textview countryGp

        scheduleViewModel.ScheduleLiveData.observe(viewLifecycleOwner) { response ->
            val nextRace = response?.body()





            if (nextRace != null) {
                val nRace = nextRace.mRData.raceTable.races[0]
                if (nextRace.mRData.raceTable.races.size > 0) {
                    val idCircuit = nRace.circuit.circuitId
                    val resources = view.context.resources
                    val resourceId =
                        resources.getIdentifier(idCircuit, "color", view.context.packageName)
                    val resourceID2 =
                        resources.getIdentifier(idCircuit + "2", "color", view.context.packageName)
                    card.setColorFilter(resources.getColor(resourceId, null))
                    Timber.tag("color").e(idCircuit)
                    card2.setColorFilter(resources.getColor(resourceID2, null))

                    ScheduleY = homeViewModel.getScheduleofTheRace(nextRace.mRData.raceTable, 0)

                    countryGp.setText(nRace.circuit.location.country)


                    nameGp.setText(homeViewModel.correctNameGP(nRace.raceName))

                    if (nRace.firstPractice != null ) {

                        val correctDate = homeViewModel.writeDate(
                            nRace.firstPractice.date,
                            nRace.date
                        )
                        dateGp.setText(correctDate)

                        //Remplissage des dernières séances

                        nomSchedule1.setText(ScheduleY[2].name)
                        dateSchedule1.setText(homeViewModel.writeDayOfTheWeekOfADate(ScheduleY[2].date))
                        timeSchedule1.setText(homeViewModel.writeTimeSeance(ScheduleY[2].hour))

                        nomSchedule2.setText(ScheduleY[3].name)
                        dateSchedule2.setText(homeViewModel.writeDayOfTheWeekOfADate(ScheduleY[3].date))
                        timeSchedule2.setText(homeViewModel.writeTimeSeance(ScheduleY[3].hour))

                        nomSchedule3.setText("Course")
                        dateSchedule3.setText(homeViewModel.writeDayOfTheWeekOfADate(ScheduleY[4].date))
                        timeSchedule3.setText(homeViewModel.writeTimeSeance(ScheduleY[4].hour))


                    }
                else {
                    Timber.tag("Error API Home").e("No data in the parameter nextRace")
                    countryGp.setText("Inconnu")
                    nameGp.setText("Inconnu")
                    dateGp.setText("Inconnu")

                        nomSchedule1.setText("A definir")
                        dateSchedule1.setText("")
                        timeSchedule1.setText("")

                        nomSchedule2.setText("A definir")
                        dateSchedule2.setText("")
                        timeSchedule2.setText("")

                        nomSchedule3.setText("A definir")
                        dateSchedule3.setText("")
                        timeSchedule3.setText("")

                    }
                }


            }
            else {
                countryGp.setText("Inconnu")
                nameGp.setText("Inconnu")
                dateGp.setText("Inconnu")
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