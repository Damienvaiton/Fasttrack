package com.iut.app.android.fasttrack.view.Fragements.Shop

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.viewModel.ShopViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailedShop : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    val shopVM by activityViewModels<ShopViewModel>()


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


        shopVM.selectedRaceLiveData.observe(viewLifecycleOwner) { response ->

            val race = response
            val resource : Resources = resources

            val resourceID : Int = resource.getIdentifier(race.circuit.circuitId, "color", requireContext().packageName)
            val resourcesID2 : Int = resource.getIdentifier(race.circuit.circuitId + "2", "color", requireContext().packageName)
            val resourcesTrack : Int = resource.getIdentifier(race.circuit.circuitId, "drawable", requireContext().packageName)




        }




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