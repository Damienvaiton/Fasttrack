package com.iut.app.android.fasttrack.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.DriverRanking
import com.iut.app.android.fasttrack.model.dataclass.schedule.Sprint

class RankingAdapter(private val ranking: DriverRanking) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameDriver = itemView.findViewById<TextView>(R.id.nameDriver)
        val positionRanking = itemView.findViewById<TextView>(R.id.numeroDriver)
        val nameTeam = itemView.findViewById<TextView>(R.id.nameConstructorDriver)
        val points = itemView.findViewById<TextView>(R.id.nbPointPiloteRanking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.ranking_card_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: RankingAdapter.ViewHolder, position: Int) {
        // Get the data model based on position


        val PrenomDriver = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].Driver.givenName.get(0)
        val NomDriver = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].Driver.familyName

        val NomTeam = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].Constructors[0].name
        val points = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].points

        // Set item views based on your views and data model
        val NomDriverTextView = viewHolder.nameDriver
        val positionClassementTextView = viewHolder.positionRanking
        val NomTeamTextView = viewHolder.nameTeam
        val pointsTextView = viewHolder.points

        //
        NomDriverTextView.setText(PrenomDriver + ". " + NomDriver)
        NomTeamTextView.setText(NomTeam)
        pointsTextView.setText(points+ " pts")
        positionClassementTextView.setText(position.plus(1).toString())
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings.size
    }
}