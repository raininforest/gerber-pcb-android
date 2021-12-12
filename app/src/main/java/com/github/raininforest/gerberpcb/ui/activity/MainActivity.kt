package com.github.raininforest.gerberpcb.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.ActivityMainBinding
import com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.GraphicsFragment
import com.github.raininforest.gerberpcb.ui.screens.layersscreen.LayersFragment
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

        if (savedInstanceState == null) {
            openFragment(LayersFragment())
        }
    }

    private fun initToolbar() {
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener(menuItemListener)
    }

    private val menuItemListener: (MenuItem) -> Boolean = {
        when (it.itemId) {
            R.id.action_layers -> {
                openFragment(LayersFragment())
                true
            }
            R.id.action_graphics -> {
                openFragment(GraphicsFragment())
                true
            }
            else -> false
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}