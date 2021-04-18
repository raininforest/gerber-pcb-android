package com.github.raininforest.gerberpcb.view

import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Layers() = FragmentScreen { LayersFragment() }
    fun Settings() = FragmentScreen { SettingsFragment() }
    fun Help() = FragmentScreen { HelpFragment() }
    fun Graphics() = FragmentScreen { GraphicsFragment() }
}