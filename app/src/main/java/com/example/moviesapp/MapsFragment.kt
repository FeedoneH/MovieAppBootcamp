package com.example.moviesapp

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviesapp.R.*
import com.example.moviesapp.databinding.PlaceInfoBinding
import com.example.moviesapp.presentation.map.MapViewModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.android.synthetic.main.place_info.*
import kotlinx.android.synthetic.main.place_info.view.*


class MapsFragment : Fragment(), OnMapReadyCallback {
    lateinit var mapViewModel: MapViewModel
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false
    lateinit var binding: PlaceInfoBinding

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_CHECK_SETTINGS = 2
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mapViewModel = (activity as MainActivity).mapViewModel
        return inflater.inflate(layout.fragment_maps, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        linearLayoutCustomView.setVisibility(View.GONE)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as MainActivity)
        locationUpdateState = true

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                if (getView() != null) {
                    placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude), "My Location")
                    mapViewModel.nearbyCinemas.observe(viewLifecycleOwner, Observer {
                        var places = it.data?.results
                        places?.forEach {
//                        it.geometry.location.lat =
                            var location = LatLng(it.geometry.location.lat, it.geometry.location.lng)
                            placeMarkerOnMap(location, "")
                        }

                    })
                }
            }
        }
        createLocationRequest()
        startLocationUpdates()
//        mapViewModel.nearbyCinemas.observe(viewLifecycleOwner, {
//
//            placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude), "mylo")
//            Log.i("newPlace", "onMapReady: ${it.data},")
//        })

    }


    override fun onStart() {
        super.onStart()
        mapViewModel = (activity as MainActivity).mapViewModel
        createLocationRequest()
    }

    private fun setUpMap() {
        if (context?.let {
                    ActivityCompat.checkSelfPermission(it,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(it,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                map.isMyLocationEnabled = true
            }
            return
        }

    }

    private fun placeMarkerOnMap(location: LatLng, title: String) {
        // 1
        val markerOptions = MarkerOptions().position(location).title(if (title.isEmpty()) null else title)
        // 2
        var locationMarker = map.addMarker(markerOptions).showInfoWindow()

    }

    private fun startLocationUpdates() {
        //1
        if (context?.let {
                    ActivityCompat.checkSelfPermission(it,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(it,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE)
            }
            return
        }
        //2
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(activity)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->

            if (e is ResolvableApiException) {
                try {

                    e.startResolutionForResult(activity as MainActivity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        if (context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED && context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(it,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                ActivityCompat.OnRequestPermissionsResultCallback { requestCode, permissions, grantResults ->

                }

            }
            return


        }

        map.isMyLocationEnabled = true
        setUpMap()


        fusedLocationClient.lastLocation.addOnSuccessListener(activity as MainActivity) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                var locationStr: String = "${location.latitude},${location.longitude}"
                mapViewModel.getNearbyCinemas("movie_theater", locationStr, "1000")
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                placeMarkerOnMap(currentLatLng, "My Location")
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
                Log.i("location", "onMapReady: $lastLocation")

            }

            fun displayCustomeInfoWindow(marker: Marker) {
                linearLayoutCustomView.setVisibility(View.VISIBLE);
                mapViewModel.nearbyCinemas.observe(viewLifecycleOwner, {
                    var places = it.data?.results
                    places?.forEach {
                        if (it.geometry.location.lat == marker.position.latitude &&
                                it.geometry.location.lng == marker.position.longitude) {
                            mapViewModel.getDetailsOfPlace(it.placeId, "name,rating,formatted_phone_number,website,geometry")
                            mapViewModel.placeDetail.observe(viewLifecycleOwner, {
                                if (it.data != null) {
                                    val place = it.data.result
                                    textViewTitle.setText(place.name);
                                    textViewOtherDetails.setText(place.rating.toString());
                                    placeLinkText.setText(place.website)
                                    placeLinkText.setOnClickListener {
                                        val i = Intent(Intent.ACTION_VIEW,Uri.parse(place.website))
                                        startActivity(i)
                                    }
                                    placDirectionBtn.setOnClickListener {
                                        val locatio = place.geometry.location
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${locatio.lat},${locatio.lng}&mode=w")).setPackage("com.google.android.apps.maps")
                                        startActivity(intent)
                                        if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
                                            requireContext().startActivity(intent);
                                        } else {
//                                            val uri = "http://maps.google.com/maps?saddr=${locatio.lat},${locatio.lng}&daddr=" + destinationLatitude.toString() + "," + destinationLongitude
//                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//                                            startActivity(intent)
                                        }
                                    }
                                }
                            }
                            )

                            closeBtn.setOnClickListener {
                                linearLayoutCustomView.setVisibility(View.GONE);
                            }
                        }

                    }

                })
            }
            map.setOnMarkerClickListener { p0 ->
                linearLayoutCustomView.setVisibility(View.GONE);
                map.animateCamera(CameraUpdateFactory.newLatLng(p0.position))
                if (linearLayoutCustomView.getVisibility() == View.VISIBLE)
                    linearLayoutCustomView.setVisibility(View.GONE);
                if (p0.position.latitude == lastLocation.latitude && p0.position.longitude == lastLocation.longitude) {
                    linearLayoutCustomView.setVisibility(View.GONE);
                } else {
                    displayCustomeInfoWindow(p0);
                }
                true;
            };

        }


    }


}