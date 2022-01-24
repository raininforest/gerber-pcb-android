package com.github.raininforest.gerberpcb

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.raininforest.gerberpcb.ui.screens.layersscreen.LayersFragment
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LayersScreenTest : TestCase() {

    @Before
    fun setup() {
        launchFragmentInContainer<LayersFragment>(
            themeResId = R.style.Theme_GerberPCB
        )
    }

    @Test
    fun performClickOnFab() {
        LayersScreen {
            fab {
                isVisible()
                isDisplayed()
                click()
            }
        }
    }
}
