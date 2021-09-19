package com.github.raininforest.gerberpcb.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.ActivityMainBinding

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener(menuItemListener)
    }

    private val menuItemListener: (MenuItem) -> Boolean = {
        when (it.itemId) {
            R.id.action_layers -> {
                findNavController(R.id.my_nav_host_fragment).navigate(R.id.layersFragment)
                true
            }
            R.id.action_graphics -> {
                findNavController(R.id.my_nav_host_fragment).navigate(R.id.graphicsFragment)
                true
            }
            R.id.action_settings -> {
                findNavController(R.id.my_nav_host_fragment).navigate(R.id.settingsFragment)
                true
            }
            R.id.action_help -> {
                findNavController(R.id.my_nav_host_fragment).navigate(R.id.helpFragment)
                true
            }
            else -> false
        }
    }
}