package com.iut.app.android.fasttrack.view.Fragements.Rankings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking.ConstructorRanking

class ConstructorRankingAdapter(private val constructorRanking: ConstructorRanking) : RecyclerView.Adapter<ConstructorRankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.constructor_ranking_card_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return constructorRanking.mRData.standingsTable.StandingsLists[0].constructorStandings.size
    }

}