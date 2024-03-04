package com.iut.app.android.fasttrack.view.Fragements.Shop

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
import com.iut.app.android.fasttrack.viewModel.ShopViewModel


class Shop : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvContacts = view.findViewById(R.id.rvShop) as RecyclerView

        val scheduleViewModel by activityViewModels<ScheduleViewModel>()
        val shopViewModel by activityViewModels<ShopViewModel>()


        scheduleViewModel.ScheduleLiveData.observe(viewLifecycleOwner) { response ->
            val calendar = response?.body()

            calendar?.let { calend ->
                val adapter = ShopAdapter(calend)

                rvContacts.adapter = adapter

                rvContacts.layoutManager = LinearLayoutManager(this.context)

                adapter.selectedRaceLD.observe(viewLifecycleOwner) {
                    shopViewModel.setSelectedRace(it)
                    val fragment = DetailedShop()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame_layout, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }
        scheduleViewModel.fetchCurrentSeason()
    }
}