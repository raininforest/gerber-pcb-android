package com.github.raininforest.gerberpcb

import com.github.raininforest.gerberpcb.ui.screens.layersscreen.LayersFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object LayersScreen : KScreen<LayersScreen>() {
    override val layoutId: Int = R.layout.layers_fragment
    override val viewClass: Class<*> = LayersFragment::class.java

    val fab = KButton { withId(R.id.fab) }
}
