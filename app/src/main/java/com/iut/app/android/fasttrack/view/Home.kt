package com.iut.app.android.fasttrack.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.viewModel.HomeViewModel
import com.iut.app.android.fasttrack.viewModel.ScheduleViewModel
import timber.log.Timber


class Home : Fragment() {
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



        var ScheduleY: ArrayList<HomeViewModel.Seance>


        scheduleViewModel.NextRaceLiveData.observe(viewLifecycleOwner) { response ->
            val nextRace = response?.body()

            Timber.tag("NextRace error").d(nextRace.toString())

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
                    Timber.tag("color").d(idCircuit)
                    card2.setColorFilter(resources.getColor(resourceID2, null))

                    ScheduleY = homeViewModel.getScheduleofTheRace(nextRace.mRData.raceTable, 0)

                    countryGp.setText(nRace.circuit.location.country)

                    trackGp.setImageResource(resources.getIdentifier(idCircuit, "drawable", view.context.packageName))



                    nameGp.setText(homeViewModel.correctNameGP(nRace.raceName))

                    if (nRace.firstPractice != null ) {

                        val correctDate = homeViewModel.writeDate(
                            nRace.firstPractice.date,
                            nRace.date
                        )
                        Timber.tag("Date error").d(correctDate)

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
                        Timber.tag("Error API Home").e("No data for hour and date")

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
            else {
                countryGp.setText("Inconnu")
                nameGp.setText("Inconnu")
                dateGp.setText("Inconnu")
            }

        }

        scheduleViewModel.getnextRace()

    }
}