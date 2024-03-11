package com.iut.app.android.fasttrack.view.Fragements.Profil

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.MultiFormatWriter
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.schedule.Schedule
import com.iut.app.android.fasttrack.model.room.Tickets.Tickets
import com.journeyapps.barcodescanner.BarcodeEncoder

class TicketsPageAdapter(private val listTicket: List<Tickets>, private val raceList: Schedule?) :
    RecyclerView.Adapter<TicketsPageAdapter.TicketsViewHolder>() {

    inner class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textBloc = itemView.findViewById<TextView>(R.id.textbloccard)
        val textSiege = itemView.findViewById<TextView>(R.id.textsiegecard)
        val namecircuit = itemView.findViewById<TextView>(R.id.nameGpcard)
        val dateGpShop = itemView.findViewById<TextView>(R.id.dateGpcard)

        val qrCode = itemView.findViewById<ImageView>(R.id.QRCodeIV)

        val cardfoncee = itemView.findViewById<ImageView>(R.id.divTicketFoncee)
        val cardclaire = itemView.findViewById<ImageView>(R.id.divTicketClaire)
        val blocblock = itemView.findViewById<ImageView>(R.id.blocblockcard)
        val blocsiege = itemView.findViewById<ImageView>(R.id.blocksiegecard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.ticket_buy_card_item, parent, false)
        return TicketsViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return listTicket.size
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {

        val ticket = listTicket[position]

        val race =
            raceList?.mRData?.raceTable?.races?.find { it.circuit.circuitId == ticket.raceId }


        val nameCircuit = holder.namecircuit
        val dateGp = holder.dateGpShop
        val textBloc = holder.textBloc
        val textSiege = holder.textSiege

        val qrCode = holder.qrCode

        val cardfoncee = holder.cardfoncee
        val cardclaire = holder.cardclaire
        val blocblock = holder.blocblock
        val blocsiege = holder.blocsiege

        nameCircuit.text = race?.circuit?.circuitName
        dateGp.text = race?.date
        textBloc.text = ticket.nameBlock
        textSiege.text = ticket.namePlace

        val idCircuit = race?.circuit?.circuitId

        val resources: Resources = holder.itemView.context.resources
        val resourceId: Int =
            resources.getIdentifier(idCircuit, "color", holder.itemView.context.packageName)
        val resourceID2 = resources.getIdentifier(idCircuit + "2", "color", holder.itemView.context.packageName)

        cardfoncee.setColorFilter(resources.getColor(resourceId,null))
        cardclaire.setColorFilter(resources.getColor(resourceID2,null))
        blocblock.setColorFilter(resources.getColor(resourceId,null))
        blocsiege.setColorFilter(resources.getColor(resourceId,null))

        val multliformat : MultiFormatWriter = MultiFormatWriter()

        val string =  "\n Ticket ID " + ticket.ticketsId + "\nName GP " + (race?.circuit?.circuitName ?: "No name") + "\n Date GP" + (race?.date
                ?: "No date") + "\n Bloc " + ticket.nameBlock + "\n Siege " + ticket.namePlace + "\n prix"+ ticket.price + "€"

        try  {
            val bitMatrix = multliformat.encode(string, com.google.zxing.BarcodeFormat.QR_CODE,qrCode.width,qrCode.height)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            qrCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}