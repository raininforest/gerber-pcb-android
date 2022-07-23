package com.github.raininforest.gerberpcb.ui.screens.layersscreen

import androidx.annotation.ColorInt
import java.util.*

/**
 * Created by Sergey Velesko on 18.07.2021
 */
data class GerberItemUi(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "<unknown name>",
    val checked: Boolean = false,
    @ColorInt val color: Int
)
