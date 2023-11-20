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
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position

        viewHolder.itemView.setOnClickListener {
            CacheDataSource.setDriver(ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver)

        }
        val prenomDriver = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver.givenName.get(0)
        val nomDriver = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver.familyName
        val IdDriver = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].driver.driverId

        val nomTeam = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].constructors[0].name
        val points = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].points
        val constructorId = ranking.mRData.standingsTable.standingsLists[0].driverStandings[position].constructors[0].constructorId

        // Set item views based on your views and data model
        val nomDriverTextView = viewHolder.nameDriver
        val resources: Resources = viewHolder.itemView.context.resources
        val resourceId: Int = resources.getIdentifier(constructorId, "color", viewHolder.itemView.context.packageName)
        val colorgold: Int = resources.getIdentifier("gold", "color", viewHolder.itemView.context.packageName)
        val colorargent: Int = resources.getIdentifier("argent", "color", viewHolder.itemView.context.packageName)
        val colorbronze: Int = resources.getIdentifier("bronze", "color", viewHolder.itemView.context.packageName)
        val colorwhite: Int = resources.getIdentifier("white", "color", viewHolder.itemView.context.packageName)
        val resourceLogoConstructor: Int = resources.getIdentifier(constructorId, "drawable", viewHolder.itemView.context.packageName)
        val resourcePhoto: Int = resources.getIdentifier(IdDriver, "drawable", viewHolder.itemView.context.packageName)
        val positionClassementTextView = viewHolder.positionRanking
        val nomTeamTextView = viewHolder.nameTeam
        val pointsTextView = viewHolder.points
        val backgroundPilote = viewHolder.background
        val ConstructorName = viewHolder.constructorName
        val PointsDriver = viewHolder.pointsDriver
        val PhotoDriver = viewHolder.photodriver
        val PhotoTeam = viewHolder.phototeam

        //
        nomDriverTextView.setText(prenomDriver + ". " + nomDriver)
        nomTeamTextView.setText(nomTeam)
        pointsTextView.setText(points+ " pts")
        positionClassementTextView.setText(position.plus(1).toString())



        if (position == 0){
            positionClassementTextView.setTextColor(resources.getColor(colorgold, null))
        }
        else if (position == 1){
            positionClassementTextView.setTextColor(resources.getColor(colorargent, null))
        }
        else if (position == 2){
            positionClassementTextView.setTextColor(resources.getColor(colorbronze, null))
        }
        else{
            positionClassementTextView.setTextColor(resources.getColor(colorwhite, null))
        }




        PhotoDriver.setImageDrawable(resources.getDrawable(resourcePhoto, null))
        PhotoTeam.setImageDrawable(resources.getDrawable(resourceLogoConstructor, null))


        backgroundPilote.setColorFilter(resources.getColor(resourceId, null))
        ConstructorName.setTextColor(resources.getColor(resourceId, null))
        PointsDriver.setTextColor(resources.getColor(resourceId, null))

    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return ranking.mRData.standingsTable.standingsLists[0].driverStandings.size
    }
}