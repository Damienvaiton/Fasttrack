package com.iut.app.android.fasttrack.view.Fragements.Shop

import android.app.AlertDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.view.Fragements.Profil.Login
import com.iut.app.android.fasttrack.viewModel.HomeViewModel
import com.iut.app.android.fasttrack.viewModel.ShopViewModel
import com.iut.app.android.fasttrack.viewModel.UserViewModel
import kotlin.random.Random


class DetailedShop : Fragment() {

    val shopVM by activityViewModels<ShopViewModel>()
    val homeVM by activityViewModels<HomeViewModel>()
    val userVM by activityViewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_shop_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val darkCard = view.findViewById<ImageView>(R.id.divScheduleFoncee)
        val lightCard = view.findViewById<ImageView>(R.id.divScheduleClaire)
        val circuitTrackIv = view.findViewById<ImageView>(R.id.scheduleTrack)
        val nameGp = view.findViewById<TextView>(R.id.nameGp)
        val country = view.findViewById<TextView>(R.id.nameGpShop)
        val dateGp = view.findViewById<TextView>(R.id.dateGp)

        val textsiege = view.findViewById<TextView>(R.id.textsiege)
        val textbloc = view.findViewById<TextView>(R.id.textbloc)
        val textsiege2 = view.findViewById<TextView>(R.id.textsiege2)
        val textbloc2 = view.findViewById<TextView>(R.id.textbloc2)
        val textsiege3 = view.findViewById<TextView>(R.id.textsiege3)
        val textbloc3 = view.findViewById<TextView>(R.id.textbloc3)
        val textsiege4 = view.findViewById<TextView>(R.id.textsiege4)
        val textbloc4 = view.findViewById<TextView>(R.id.textbloc4)

        val bigSquare1 = view.findViewById<ImageView>(R.id.grandCarreBillet)
        val bigSquare2 = view.findViewById<ImageView>(R.id.grandCarreBillet2)
        val bigSquare3 = view.findViewById<ImageView>(R.id.grandCarreBillet3)
        val bigSquare4 = view.findViewById<ImageView>(R.id.grandCarreBillet4)

        val blocBlock1 = view.findViewById<ImageView>(R.id.blockbloc)
        val blocBlock2 = view.findViewById<ImageView>(R.id.blockbloc2)
        val blocBlock3 = view.findViewById<ImageView>(R.id.blockbloc3)
        val blocBlock4 = view.findViewById<ImageView>(R.id.blockbloc4)

        val blocBuy1 = view.findViewById<ImageView>(R.id.blockbuy)
        val blocBuy2 = view.findViewById<ImageView>(R.id.blockbuy2)
        val blocBuy3 = view.findViewById<ImageView>(R.id.blockbuy3)
        val blocBuy4 = view.findViewById<ImageView>(R.id.blockbuy4)

        val blocSiege1 = view.findViewById<ImageView>(R.id.blocksiege)
        val blocSiege2 = view.findViewById<ImageView>(R.id.blocksiege2)
        val blocSiege3 = view.findViewById<ImageView>(R.id.blocksiege3)
        val blocSiege4 = view.findViewById<ImageView>(R.id.blocksiege4)


        shopVM.selectedRaceLiveData.observe(viewLifecycleOwner) { response ->

            val race = response
            val resource: Resources = resources

            val resourceID: Int = resource.getIdentifier(
                race.circuit.circuitId,
                "color",
                requireContext().packageName
            )
            val resourcesID2: Int = resource.getIdentifier(
                race.circuit.circuitId + "2",
                "color",
                requireContext().packageName
            )
            val resourcesTrack: Int = resource.getIdentifier(
                race.circuit.circuitId,
                "drawable",
                requireContext().packageName
            )

            darkCard.setColorFilter(resources.getColor(resourceID, null))
            lightCard.setColorFilter(resources.getColor(resourcesID2, null))

            bigSquare1.setColorFilter(resources.getColor(resourceID, null))
            bigSquare2.setColorFilter(resources.getColor(resourceID, null))
            bigSquare3.setColorFilter(resources.getColor(resourceID, null))
            bigSquare4.setColorFilter(resources.getColor(resourceID, null))


            blocBlock1.setColorFilter(resources.getColor(resourcesID2, null))
            blocBlock2.setColorFilter(resources.getColor(resourcesID2, null))
            blocBlock3.setColorFilter(resources.getColor(resourcesID2, null))
            blocBlock4.setColorFilter(resources.getColor(resourcesID2, null))

            blocSiege1.setColorFilter(resources.getColor(resourcesID2, null))
            blocSiege2.setColorFilter(resources.getColor(resourcesID2, null))
            blocSiege3.setColorFilter(resources.getColor(resourcesID2, null))
            blocSiege4.setColorFilter(resources.getColor(resourcesID2, null))

            circuitTrackIv.setImageResource(resourcesTrack)

            nameGp.text = race.raceName
            country.text = race.circuit.location.country
            dateGp.text = if (race.firstPractice != null) {
                homeVM.writeDate(race.firstPractice.date, race.date)
            } else {
                race.date
            }

            // Bloc + une lettre aleatoire
            textbloc.text = "Bloc " + ('A'..'E').random()
            textbloc2.text = "Bloc " + ('E'..'L').random()
            textbloc3.text = "Bloc " + ('L'..'Z').random()
            textbloc4.text = "Bloc " + ('L'..'Z').random()

            // Siege + un chiffre aleatoire

            textsiege.text = "Siege " + Random.nextInt(1, 100).toString()
            textsiege2.text = "Siege " + Random.nextInt(1, 100).toString()
            textsiege3.text = "Siege " + Random.nextInt(1, 100)
            textsiege4.text = "Siege " + Random.nextInt(1, 100)

            blocBuy1.setOnClickListener {
                if (userVM.isUserConnected()) {

                    userVM.ticketsResponse.observe(viewLifecycleOwner) {
                        if (it) {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Billet acheté")
                                .setMessage("Votre billet a bien été acheté")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        } else {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Erreur")
                                .setMessage("Une erreur est survenue")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        }
                    }

                    userVM.insertTicket(
                        1200,
                        userVM.getFanConnected()!!.id,
                        race.circuit.circuitId,
                        "GrandStand",
                        textbloc.text.toString(),
                        textsiege.text.toString()
                    )

                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Vous n'êtes pas connecté")
                        .setMessage("Vous devez être connecté pour acheter un billet")
                        .setPositiveButton("Se connecter") { dialog, which ->
                            val fragment = Login()
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.replace(R.id.frame_layout, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                        .setNegativeButton("Annuler") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }

            blocBuy2.setOnClickListener {
                if (userVM.isUserConnected()) {

                    userVM.ticketsResponse.observe(viewLifecycleOwner) {
                        if (it) {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Billet acheté")
                                .setMessage("Votre billet a bien été acheté")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        } else {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Erreur")
                                .setMessage("Une erreur est survenue")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        }
                    }

                    userVM.insertTicket(
                        909,
                        userVM.getFanConnected()!!.id,
                        race.circuit.circuitId,
                        "GrandStand",
                        textbloc2.text.toString(),
                        textsiege2.text.toString()
                    )

                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Vous n'êtes pas connecté")
                        .setMessage("Vous devez être connecté pour acheter un billet")
                        .setPositiveButton("Se connecter") { dialog, which ->
                            val fragment = Login()
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.replace(R.id.frame_layout, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                        .setNegativeButton("Annuler") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }

            blocBuy3.setOnClickListener {
                if (userVM.isUserConnected()) {

                    userVM.ticketsResponse.observe(viewLifecycleOwner) {
                        if (it) {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Billet acheté")
                                .setMessage("Votre billet a bien été acheté")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        } else {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Erreur")
                                .setMessage("Une erreur est survenue")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        }
                    }

                    userVM.insertTicket(
                        659,
                        userVM.getFanConnected()!!.id,
                        race.circuit.circuitId,
                        "GrandStand",
                        textbloc3.text.toString(),
                        textsiege3.text.toString()
                    )

                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Vous n'êtes pas connecté")
                        .setMessage("Vous devez être connecté pour acheter un billet")
                        .setPositiveButton("Se connecter") { dialog, which ->
                            val fragment = Login()
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.replace(R.id.frame_layout, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                        .setNegativeButton("Annuler") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }

            blocBuy4.setOnClickListener {
                if (userVM.isUserConnected()) {

                    userVM.ticketsResponse.observe(viewLifecycleOwner) {
                        if (it) {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Billet acheté")
                                .setMessage("Votre billet a bien été acheté")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        } else {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Erreur")
                                .setMessage("Une erreur est survenue")
                                .setPositiveButton("OK") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        }
                    }

                    userVM.insertTicket(
                        599,
                        userVM.getFanConnected()!!.id,
                        race.circuit.circuitId,
                        "GrandStand",
                        textbloc4.text.toString(),
                        textsiege4.text.toString()
                    )

                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Vous n'êtes pas connecté")
                        .setMessage("Vous devez être connecté pour acheter un billet")
                        .setPositiveButton("Se connecter") { dialog, which ->
                            val fragment = Login()
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.replace(R.id.frame_layout, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                        .setNegativeButton("Annuler") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }


        }

        shopVM.getSelectedRace()


    }

}