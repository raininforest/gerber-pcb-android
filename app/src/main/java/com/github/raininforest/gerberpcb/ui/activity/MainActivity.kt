package com.github.raininforest.gerberpcb.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.ActivityMainBinding
import com.github.raininforest.logger.Logger

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
        val navController = findNavController(R.id.my_nav_host_fragment)
        when (it.itemId) {
            R.id.action_layers -> {
                navController.navigate(R.id.layersFragment)
                Logger.d("Navigate to Layers screen")
                true
            }
            R.id.action_graphics -> {
                navController.navigate(R.id.graphicsFragment)
                Logger.d("Navigate to Graphics screen")
                true
            }
            R.id.action_help -> {
                navController.navigate(R.id.helpFragment)
                Logger.d("Navigate to Help screen")
                true
            }
            else -> false
        }
    }
}