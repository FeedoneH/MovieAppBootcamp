package com.example.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_settings.*


/**
 * A fragment representing a list of Items.
 */
class SettingsFragment : Fragment() {

var menuList= listOf("About","Licences")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutBtn.setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }
        licensesBtn.setOnClickListener {
            findNavController().navigate(R.id.licenseFragment)
        }
    }


}