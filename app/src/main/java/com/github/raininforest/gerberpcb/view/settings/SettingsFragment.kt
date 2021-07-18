package com.github.raininforest.gerberpcb.view.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.github.raininforest.gerberpcb.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}