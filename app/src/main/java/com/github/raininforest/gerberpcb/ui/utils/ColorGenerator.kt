package com.github.raininforest.gerberpcb.ui.utils

import android.content.Context
import androidx.annotation.ColorInt
import com.github.raininforest.gerberpcb.R
import kotlin.random.Random

class ColorGenerator(private val context: Context) {

    private val colors = listOf(
        context.resources.getColor(R.color.color1),
        context.resources.getColor(R.color.color2),
        context.resources.getColor(R.color.color3),
        context.resources.getColor(R.color.color4),
        context.resources.getColor(R.color.color5),
        context.resources.getColor(R.color.color6),
        context.resources.getColor(R.color.color7),
        context.resources.getColor(R.color.color8)
    )

    @ColorInt
    fun generateInitialLayerColor(): Int {
        val colorCount = colors.size
        return colors[Random.nextInt(0, colorCount - 1)]
    }
}
