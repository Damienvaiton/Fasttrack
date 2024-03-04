package com.iut.app.android.fasttrack.view.Fragements.Rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.schedule.Sprint
import com.iut.app.android.fasttrack.view.DriverRankingAdapter
import com.iut.app.android.fasttrack.viewModel.RankingViewModel


class Ranking : Fragment() {


    lateinit var contacts: ArrayList<Sprint>
    var IsConstructor = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val tabLayout = view.findViewById(R.id.tabLayout2) as TabLayout

        val rankingViewModel by activityViewModels<RankingViewModel>()

        // Initialisez vos adaptateurs ici (si nécessaire)
        //val driverAdapter = DriverRankingAdapter(emptyList())
        //val constructorAdapter = ConstructorRankingAdapter(emptyList())
        val driverAdapter = DriverRankingAdapter(DriverRankingAdapter.createEmpty())
        val constructorAdapter = ConstructorRankingAdapter(ConstructorRankingAdapter.createEmpty())

        rvContacts.layoutManager = LinearLayoutManager(context)

        rankingViewModel.fetchDriverRanking()
        rankingViewModel.driverLiveDataRanking.observe(viewLifecycleOwner) { response ->
            driverAdapter.updateData(response)
            rvContacts.adapter = driverAdapter
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Onglet "Drivers"
                        rankingViewModel.fetchDriverRanking()
                        rankingViewModel.driverLiveDataRanking.observe(viewLifecycleOwner) { response ->
                            driverAdapter.updateData(response) // Mettre à jour l'adaptateur avec de nouvelles données
                            rvContacts.adapter = driverAdapter
                        }
                    }

                    1 -> { // Onglet "Teams"
                        rankingViewModel.fetchConstructorRanking()
                        rankingViewModel.constructorLiveDataRanking.observe(viewLifecycleOwner) { response ->
                            constructorAdapter.updateData(response) // Mettre à jour l'adaptateur avec de nouvelles données
                            rvContacts.adapter = constructorAdapter
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { /* ... */
            }

            override fun onTabReselected(tab: TabLayout.Tab?) { /* ... */
            }
        })

        // Initialisation par défaut
        tabLayout.getTabAt(0)?.select()
    }
}