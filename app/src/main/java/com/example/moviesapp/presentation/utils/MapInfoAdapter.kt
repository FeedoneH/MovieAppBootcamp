package com.example.moviesapp.presentation.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.place_info.view.*

class MapInfoAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
    var markerInfoWindow = (context as MainActivity).layoutInflater.inflate(R.layout.place_info, null)
    var mapWrapperLayout: MapWrapperLayout = MapWrapperLayout(context)
    private fun bindText(marker: Marker, view: View) {
        view.placeName.text = marker.snippet
        view.placeName.text = "hell"
        view.placeBtn.setOnClickListener {
            Toast.makeText((it.context), "clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getInfoWindow(p0: Marker): View {
        bindText(p0, markerInfoWindow)
        return markerInfoWindow
    }

    override fun getInfoContents(p0: Marker): View {
        bindText(p0, markerInfoWindow)
        mapWrapperLayout.setMarkerWithInfoWindow(p0, markerInfoWindow);
        return markerInfoWindow
    }
    fun getPixelsFromDp(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}