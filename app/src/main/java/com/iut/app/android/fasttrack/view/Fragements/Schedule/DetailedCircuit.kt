package com.iut.app.android.fasttrack.view.Fragements.Schedule

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.dataclass.schedule.Circuit
import com.iut.app.android.fasttrack.viewModel.ScheduleViewModel


class DetailedCircuit : Fragment(), OnMapReadyCallback {


    val ScheduleVM by activityViewModels<ScheduleViewModel>()

    private lateinit var mMap: GoogleMap

    private lateinit var circuit: Circuit


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragement_detailed_circuit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fond = view.findViewById<ImageView>(R.id.fondGp)
        val fond2 = view.findViewById<ImageView>(R.id.scheduleBack2)
        val circuitTrackIv = view.findViewById<ImageView>(R.id.scheduleTrack)
        val nameContryTv = view.findViewById<TextView>(R.id.CoundtryGp)
        val circuitNameTv = view.findViewById<TextView>(R.id.nameCircuitGp)
        val circuitLatitudeTv = view.findViewById<TextView>(R.id.LatitudeGp)
        val circuitLongitudeTv = view.findViewById<TextView>(R.id.LongitudeGp)
        val maps = view.findViewById<MapView>(R.id.mapView2)

        ScheduleVM.DetailCircuitLiveData.observe(viewLifecycleOwner) { response ->
            circuit = response


            val Resources: Resources = resources
            val resourceId: Int =
                Resources.getIdentifier(circuit?.circuitId, "color", context?.packageName)
            val resourceId2: Int =
                Resources.getIdentifier(circuit?.circuitId + "2", "color", context?.packageName)
            val resourceIdTrack: Int =
                Resources.getIdentifier(circuit?.circuitId, "drawable", context?.packageName)
            fond.setColorFilter(resources.getColor(resourceId, null))
            fond2.setColorFilter(resources.getColor(resourceId2, null))
            circuitNameTv.text = circuit?.circuitName
            circuitTrackIv.setImageDrawable(resources.getDrawable(resourceIdTrack, null))
            nameContryTv.text = circuit?.location?.country
            circuitLatitudeTv.text = "Latitude : " + circuit?.location?.lat
            circuitLongitudeTv.text = "Longitude : " + circuit?.location?.long


            //Map
            maps.onCreate(savedInstanceState)
            maps.onResume()
            maps.getMapAsync(this)

            //Click Listener
            fond.setOnClickListener {
                activity?.onBackPressed()
            }

        }
        ScheduleVM.getDetailCircuit()
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val circuit = CacheDataSource.getCircuit()
        val lat = circuit?.location?.lat?.toDouble()
        val long = circuit?.location?.long?.toDouble()
        val latLng = com.google.android.gms.maps.model.LatLng(lat!!, long!!)
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(circuit.circuitName)
        )


        mMap.moveCamera(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                latLng,
                5f
            )

        )

        mMap.setOnMapLongClickListener {
            mMap.moveCamera(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                    latLng,
                    5f
                )
            )
        }

    }

}