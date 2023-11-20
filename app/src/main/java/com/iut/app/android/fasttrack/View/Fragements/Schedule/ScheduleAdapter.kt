package com.iut.app.android.fasttrack.View.Fragements.Schedule

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.schedule.Location
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import com.iut.app.android.fasttrack.viewModel.HomeViewModel

class ScheduleAdapter (private val calendar: Schedule) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        val cardView = itemView.findViewById<CardView>(R.id.cardSchedule)
        // for any view that will be set as you render a row
        val nameGPTextView = itemView.findViewById<TextView>(R.id.nameGpSchedule)

        val dateGPTextView = itemView.findViewById<TextView>(R.id.DateGPSchedule)

        val winnerImg = itemView.findViewById<ImageView>(R.id.divScheduleGagnant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.schedule_card_itemtest, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val race = calendar.mRData.raceTable.races[position]
        viewHolder.itemView.setOnClickListener {
        }
        val idCircuit = race.circuit.circuitId
        Log.e("idCircuit", idCircuit)

        //Get the color from the color.xml and set the color of the card
        val resources: Resources = viewHolder.itemView.context.resources
        val resourceId: Int = resources.getIdentifier(idCircuit, "color", viewHolder.itemView.context.packageName)
        val resourceID2 = resources.getIdentifier(idCircuit + "2", "color", viewHolder.itemView.context.packageName)
        Log.e("color", resourceId.toString())
        viewHolder.cardView.setCardBackgroundColor(resources.getColor(resourceId, null))

        val winnerImg = viewHolder.winnerImg

        winnerImg.setColorFilter(resources.getColor(resourceID2, null))



        val homeVM = HomeViewModel()

        val dateTextView = viewHolder.dateGPTextView

       var date = homeVM.writeDate(race.firstPractice.date, race.date)



        val contact: Location = race.circuit.location
        // Set item views based on your views and data model
        val countryTextView = viewHolder.nameGPTextView
        countryTextView.setText(contact.country)
        dateTextView.setText(date)
    }



    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return calendar.mRData.raceTable.races.size
    }
}