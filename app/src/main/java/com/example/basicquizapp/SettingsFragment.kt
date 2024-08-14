package com.example.basicquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val soundSwitch: Switch = view.findViewById(R.id.soundSwitch)
        val notificationsSwitch: Switch = view.findViewById(R.id.notificationsSwitch)

        // Add logic to handle switch states (save preferences, etc.)
        soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save sound preference
        }

        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save notification preference
        }

        return view
    }
}
