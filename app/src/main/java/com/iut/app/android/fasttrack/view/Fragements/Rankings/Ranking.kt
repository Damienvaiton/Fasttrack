package com.iut.app.android.fasttrack.view.Fragements.Rankings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.view.DriverRankingAdapter
import com.iut.app.android.fasttrack.model.dataclass.schedule.Sprint
import com.iut.app.android.fasttrack.viewModel.RankingViewModel
import java.sql.Driver

/**
 * A simple [Fragment] subclass.
 * Use the [Ranking.newInstance] factory method to
 * create an instance of this fragment.
 */

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Ranking : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var contacts: ArrayList<Sprint>
    var IsConstructor = false

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
        return inflater.inflate(R.layout.fragment_ranking_driv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvContacts = view.findViewById(R.id.rvRanking) as RecyclerView
        val driverSelector = view.findViewById(R.id.submitDriver) as Button
        val constructorSelector = view.findViewById(R.id.submitTeam) as Button

        val rankingViewModel by activityViewModels<RankingViewModel>()

        rankingViewModel.fetchDriverRanking()
        rankingViewModel.fetchConstructorRanking()

        driverSelector.setOnClickListener {
            rankingViewModel.fetchConstructorRanking()
            IsConstructor = false
        }

        constructorSelector.setOnClickListener {
            rankingViewModel.fetchDriverRanking()
            IsConstructor = true
        }


            rankingViewModel.driverLiveDataRanking.observe(viewLifecycleOwner) { response ->

                val Ranking = response

                val adapter = DriverRankingAdapter(Ranking)

                rvContacts.adapter = adapter

                rvContacts.layoutManager = LinearLayoutManager(this.context)
            }



            rankingViewModel.constructorLiveDataRanking.observe(viewLifecycleOwner) { response ->

                val Ranking = response
                Log.e("Ranking", Ranking.toString())
                val adapter = ConstructorRankingAdapter(Ranking)

                rvContacts.adapter = adapter

                rvContacts.layoutManager = LinearLayoutManager(this.context)
            }




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Ranking.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Ranking().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}