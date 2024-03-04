package com.iut.app.android.fasttrack.view.Fragements.Shop

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
import com.iut.app.android.fasttrack.viewModel.HomeViewModel
import com.iut.app.android.fasttrack.viewModel.ShopViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailedShop : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    val shopVM by activityViewModels<ShopViewModel>()
    val homeVM by activityViewModels<HomeViewModel>()


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

        val bigSquare1 = view.findViewById<ImageView>(R.id.grandCarreBillet)
        val bigSquare2 = view.findViewById<ImageView>(R.id.grandCarreBillet2)
        val bigSquare3 = view.findViewById<ImageView>(R.id.grandCarreBillet3)
        val bigSquare4 = view.findViewById<ImageView>(R.id.grandCarreBillet4)

        val blocBlock1 = view.findViewById<ImageView>(R.id.blockbloc)
        val blocBlock2 = view.findViewById<ImageView>(R.id.blockbloc2)
        val blocBlock3 = view.findViewById<ImageView>(R.id.blockbloc3)
        val blocBlock4 = view.findViewById<ImageView>(R.id.blockbloc4)

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


        }

        shopVM.getSelectedRace()


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailedShop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}