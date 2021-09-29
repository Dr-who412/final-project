package com.example.umum
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsFragment : Fragment(), OnMapReadyCallback {
    var currentLocation : Location? = null
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    val REQUEST_CODE = 101
    lateinit var restaurantName:String
    var restaurantLocationLongitude =0.0
    var restaurantLocationLatitude =0.0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        takeArg()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        fetchLocation()

    }
    @SuppressLint("UseRequireInsteadOfGet")
    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(view!!.context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view!!.context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE)
            return
        }

        val task = fusedLocationProviderClient!!.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null){
                currentLocation = location
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment?.getMapAsync(this)
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
        val latLng2 = LatLng(restaurantLocationLatitude, restaurantLocationLongitude)
        val markerOptions = MarkerOptions().position(latLng).title("I Am Here!")
        val markerOptions2 = MarkerOptions().position(latLng2).title(restaurantName)
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng2))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
        googleMap.addMarker(markerOptions)
        googleMap.addMarker(markerOptions2)

        var line = googleMap.addPolyline(PolylineOptions().add(latLng,latLng2))
        line.isGeodesic = true
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLocation()
                }
                else
                    Toast.makeText(context,"Location permissions not access\nplease go to settings and assess it",Toast.LENGTH_LONG).show()

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    fun takeArg(){
        restaurantName = arguments?.getString("restaurantName").toString()
        restaurantLocationLongitude = arguments?.getString("restaurantLocationLongitude").toString().toDouble()
        restaurantLocationLatitude = arguments?.getString("restaurantLocationLatitude").toString().toDouble()

    }

}