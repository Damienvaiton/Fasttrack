package com.iut.app.android.fasttrack.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.schedule.Sprint

class RankingAdapter(private val mContacts: List<Sprint>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.nameGpSchedule)
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
        val contact: Sprint = mContacts.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText(contact.date)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mContacts.size
    }
}