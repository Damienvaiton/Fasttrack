package com.iut.app.android.fasttrack.view.Fragements.Schedule

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import timber.log.Timber

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailedCircuit : Fragment(), OnMapReadyCallback {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragement_detailed_circuit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fond = view.findViewById<ImageView>(R.id.fondGp)
        val circuitTrackIv = view.findViewById<ImageView>(R.id.scheduleTrack)
        val nameContryTv = view.findViewById<TextView>(R.id.CoundtryGp)
        val circuitNameTv = view.findViewById<TextView>(R.id.nameCircuitGp)
        val circuitLatitudeTv = view.findViewById<TextView>(R.id.LatitudeGp)
        val circuitLongitudeTv = view.findViewById<TextView>(R.id.LongitudeGp)
        val maps = view.findViewById<MapView>(R.id.mapView2)

        val circuit = CacheDataSource.getCircuit()


        val Resources: Resources = resources
        val resourceId: Int = Resources.getIdentifier(circuit?.circuitId, "color", context?.packageName)
        fond.setColorFilter(resources.getColor(resourceId,null))
        circuitNameTv.text = circuit?.circuitName
        nameContryTv.text = circuit?.location?.country
        circuitLatitudeTv.text = circuit?.location?.lat
        circuitLongitudeTv.text = circuit?.location?.long


        //Map
        maps.onCreate(savedInstanceState)
        maps.onResume()
        maps.getMapAsync(this)

        //Click Listener
        fond.setOnClickListener {
            //retour au fragment précédent
            activity?.onBackPressed()
        }



    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailedCircuit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMapReady(p0: GoogleMap) {
        Timber.e("onMapReady")
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
        mMap.uiSettings.isZoomGesturesEnabled = false
        mMap.uiSettings.isScrollGesturesEnabled = false


        mMap.moveCamera(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                latLng,
                5f
            )

        )

    }

}