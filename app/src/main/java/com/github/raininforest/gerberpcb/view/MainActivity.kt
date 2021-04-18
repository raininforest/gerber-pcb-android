package com.github.raininforest.gerberpcb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.app.App
import com.github.raininforest.gerberpcb.databinding.ActivityMainBinding
import com.github.raininforest.gerberpcb.view.Screens.Graphics
import com.github.raininforest.gerberpcb.view.Screens.Help
import com.github.raininforest.gerberpcb.view.Screens.Layers
import com.github.raininforest.gerberpcb.view.Screens.Settings
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.mainContainer)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        if (savedInstanceState == null) {
            App.router.navigateTo(Layers())
        }
    }

    override fun onResume() {
        super.onResume()
        App.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.navigatorHolder.removeNavigator()
    }

    private fun initToolbar() {
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener(menuItemListener)
    }

    private val menuItemListener: (MenuItem) -> Boolean = {
        when (it.itemId) {
            R.id.action_layers -> {
                App.router.navigateTo(Layers())
                true
            }
            R.id.action_graphics -> {
                App.router.navigateTo(Graphics())
                true
            }
            R.id.action_settings -> {
                App.router.navigateTo(Settings())
                true
            }
            R.id.action_help -> {
                App.router.navigateTo(Help())
                true
            }
            else -> false
        }
    }
}