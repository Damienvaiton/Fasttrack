package com.iut.app.android.fasttrack.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.schedule.Location

class ScheduleAdapter (private val mContacts: List<Location>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.nameGpSchedule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.schedule_card_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ScheduleAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val contact: Location = mContacts.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText(contact.country)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mContacts.size
    }
}