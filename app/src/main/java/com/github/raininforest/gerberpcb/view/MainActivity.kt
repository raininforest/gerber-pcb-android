package com.github.raininforest.gerberpcb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentScreen: ScreenName = ScreenName.NULL
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        if (savedInstanceState == null) {
            openScreen(ScreenName.OPEN)
        }
    }

    private fun initToolbar() {
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener(menuItemListener)
    }

    private val menuItemListener: (MenuItem) -> Boolean = {
        when (it.itemId) {
            R.id.action_open -> {
                openScreen(ScreenName.OPEN)
                true
            }
            R.id.action_layers -> {
                openScreen(ScreenName.LAYERS)
                true
            }
            R.id.action_graphics -> {
                openScreen(ScreenName.GRAPHICS)
                true
            }
            R.id.action_settings -> {
                openScreen(ScreenName.SETTINGS)
                true
            }
            R.id.action_help -> {
                openScreen(ScreenName.HELP)
                true
            }
            else -> false
        }
    }

    private fun openScreen(screen: ScreenName) {
        if (currentScreen == screen) return

        val fragmentToSet: Fragment = when (screen) {
            ScreenName.OPEN -> OpenFragment.newInstance()
            ScreenName.LAYERS -> LayersFragment.newInstance()
            ScreenName.GRAPHICS -> GraphicsFragment.newInstance()
            ScreenName.SETTINGS -> SettingsFragment()
            ScreenName.HELP -> HelpFragment()
            else -> return
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, fragmentToSet)
            .commit()
        currentScreen = screen
    }
}