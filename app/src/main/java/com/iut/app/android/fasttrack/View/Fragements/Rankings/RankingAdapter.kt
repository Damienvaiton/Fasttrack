package com.iut.app.android.fasttrack.View.Fragements.Rankings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.dataclass.Rankings.DriverRanking.DriverRanking

class RankingAdapter(private val ranking: DriverRanking) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameDriver = itemView.findViewById<TextView>(R.id.nameDriver)
        val positionRanking = itemView.findViewById<TextView>(R.id.numeroDriver)
        val nameTeam = itemView.findViewById<TextView>(R.id.nameConstructorDriver)
        val points = itemView.findViewById<TextView>(R.id.nbPointPiloteRanking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.ranking_card_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position

        viewHolder.itemView.setOnClickListener {
            CacheDataSource.setDriver(ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver)

        }
        val prenomDriver = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver.givenName.get(0)
        val nomDriver = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver.familyName

        val nomTeam = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].constructors[0].name
        val points =  ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].points

        // Set item views based on your views and data model
        val nomDriverTextView = viewHolder.nameDriver
        val positionClassementTextView = viewHolder.positionRanking
        val nomTeamTextView = viewHolder.nameTeam
        val pointsTextView = viewHolder.points

        //
        nomDriverTextView.setText(prenomDriver + ". " + nomDriver)
        nomTeamTextView.setText(nomTeam)
        pointsTextView.setText(points+ " pts")
        positionClassementTextView.setText(position.plus(1).toString())
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return ranking.mRData.standingsTable.standingsLists[0].driverStandings.size
    }
}