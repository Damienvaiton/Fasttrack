package com.iut.app.android.fasttrack.View

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val positionRanking = itemView.findViewById<TextView>(R.id.rankingDriver)
        val nameTeam = itemView.findViewById<TextView>(R.id.nameConstructorDriver)
        val points = itemView.findViewById<TextView>(R.id.nbPointPiloteRanking)
        val background = itemView.findViewById<ImageView>(R.id.divRankingPrincipal2)
        val constructorName = itemView.findViewById<TextView>(R.id.nameConstructorDriver)
        val pointsDriver = itemView.findViewById<TextView>(R.id.nbPointPiloteRanking)
        val photodriver = itemView.findViewById<ImageView>(R.id.imgdriver)
        val phototeam = itemView.findViewById<ImageView>(R.id.imgteam)
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
        val IdDriver = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].Driver.driverId

        val NomTeam = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].Constructors[0].name
        val points = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].points
        val constructorId = ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings[position].Constructors[0].constructorId

        // Set item views based on your views and data model
        val NomDriverTextView = viewHolder.nameDriver
        val resources: Resources = viewHolder.itemView.context.resources
        val resourceId: Int = resources.getIdentifier(constructorId, "color", viewHolder.itemView.context.packageName)
        val colorgold: Int = resources.getIdentifier("gold", "color", viewHolder.itemView.context.packageName)
        val colorargent: Int = resources.getIdentifier("argent", "color", viewHolder.itemView.context.packageName)
        val colorbronze: Int = resources.getIdentifier("bronze", "color", viewHolder.itemView.context.packageName)
        val resourceLogoConstructor: Int = resources.getIdentifier(constructorId, "drawable", viewHolder.itemView.context.packageName)
        val resourcePhoto: Int = resources.getIdentifier(IdDriver, "drawable", viewHolder.itemView.context.packageName)
        val positionClassementTextView = viewHolder.positionRanking
        val NomTeamTextView = viewHolder.nameTeam
        val pointsTextView = viewHolder.points
        val backgroundPilote = viewHolder.background
        val ConstructorName = viewHolder.constructorName
        val PointsDriver = viewHolder.pointsDriver
        val PhotoDriver = viewHolder.photodriver
        val PhotoTeam = viewHolder.phototeam

        //
        NomDriverTextView.setText(PrenomDriver + ". " + NomDriver)
        NomTeamTextView.setText(NomTeam)
        pointsTextView.setText(points+ " pts")
        positionClassementTextView.setText(position.plus(1).toString())

        val rank = position
        if (rank == 0){
            positionClassementTextView.setTextColor(resources.getColor(colorgold, null))
        }
        if (rank == 1){
            positionClassementTextView.setTextColor(resources.getColor(colorargent, null))
        }
        if (rank == 2){
            positionClassementTextView.setTextColor(resources.getColor(colorbronze, null))
        }

        PhotoDriver.setImageDrawable(resources.getDrawable(resourcePhoto, null))
        PhotoTeam.setImageDrawable(resources.getDrawable(resourceLogoConstructor, null))


        backgroundPilote.setColorFilter(resources.getColor(resourceId, null))
        ConstructorName.setTextColor(resources.getColor(resourceId, null))
        PointsDriver.setTextColor(resources.getColor(resourceId, null))

    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return ranking.MRData.StandingsTable.StandingsLists[0].DriverStandings.size
    }
}