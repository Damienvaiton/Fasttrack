package com.iut.app.android.fasttrack.View

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.schedule.Location
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule

class ScheduleAdapter (private val calendar: Schedule) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        val cardView = itemView.findViewById<CardView>(R.id.cardSchedule)
        // for any view that will be set as you render a row
        val nameGPTextView = itemView.findViewById<TextView>(R.id.nameGpSchedule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.schedule_card_itemtest, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ScheduleAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val idCircuit = calendar.MRData.RaceTable.Races[position].Circuit.circuitId

        //Get the color from the color.xml and set the color of the card
        val resources: Resources = viewHolder.itemView.context.resources
        val resourceId: Int = resources.getIdentifier(idCircuit, "color", viewHolder.itemView.context.packageName)
        viewHolder.cardView.setCardBackgroundColor(resources.getColor(resourceId))



        val contact: Location = calendar.MRData.RaceTable.Races[position].Circuit.Location
        // Set item views based on your views and data model
        val CountryTextView = viewHolder.nameGPTextView
        CountryTextView.setText(contact.country)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return calendar.MRData.RaceTable.Races.size
    }
}