package com.iut.app.android.fasttrack.view.Fragements.Rankings

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.Rankings.ConstructorRanking.ConstructorRanking
import org.w3c.dom.Text

class ConstructorRankingAdapter(private val constructorRanking: ConstructorRanking) : RecyclerView.Adapter<ConstructorRankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameConstructor = itemView.findViewById<TextView>(R.id.nameConstructorDriver)
        val positionRanking = itemView.findViewById<TextView>(R.id.rankingDriver)
        val points = itemView.findViewById<TextView>(R.id.nbPointPiloteRanking)
        val imgConstructor = itemView.findViewById<ImageView>(R.id.imgteam)
        val background = itemView.findViewById<ImageView>(R.id.divRankingPrincipal2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.constructor_ranking_card_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val nameConstructor = constructorRanking.mRData.standingsTable.standingsLists[0].constructorStandings[position].constructor.name
        val positionRanking = constructorRanking.mRData.standingsTable.standingsLists[0].constructorStandings[position].position
        val points = constructorRanking.mRData.standingsTable.standingsLists[0].constructorStandings[position].points
        val constructorId = constructorRanking.mRData.standingsTable.standingsLists[0].constructorStandings[position].constructor.constructorId

        val resources: Resources = holder.itemView.context.resources
        val colorgold: Int = resources.getIdentifier("gold", "color", holder.itemView.context.packageName)
        val colorargent: Int = resources.getIdentifier("argent", "color", holder.itemView.context.packageName)
        val colorbronze: Int = resources.getIdentifier("bronze", "color", holder.itemView.context.packageName)
        val resourceId: Int = resources.getIdentifier(constructorId, "color", holder.itemView.context.packageName)
        val resourceLogoConstructor: Int = resources.getIdentifier(constructorId, "drawable", holder.itemView.context.packageName)

        val positionTv = holder.positionRanking
        val name = holder.nameConstructor
        val point = holder.points
        val imgConstructor = holder.imgConstructor

        positionTv.setText(positionRanking.toString())
        name.text = nameConstructor
        point.text = points + " pts"
        imgConstructor.setImageResource(resourceLogoConstructor)
        holder.background.setColorFilter(resources.getColor(resourceId, null))
        holder.nameConstructor.setTextColor(resources.getColor(resourceId, null))
        holder.points.setTextColor(resources.getColor(resourceId, null))

        if (position == 0){
            positionTv.setTextColor(resources.getColor(colorgold, null))
        }
        else if (position == 1){
            positionTv.setTextColor(resources.getColor(colorargent, null))
        }
        else if (position == 2) {
            positionTv.setTextColor(resources.getColor(colorbronze, null))
        }
        else{
            positionTv.setTextColor(resources.getColor(resourceId, null))
        }
    }

    override fun getItemCount(): Int {
        return constructorRanking.mRData.standingsTable.standingsLists[0].constructorStandings.size
    }

}