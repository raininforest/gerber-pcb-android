package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import android.graphics.Canvas
import com.github.raininforest.GraphicsObject

interface GerberRenderer {
    fun render(data: List<GraphicsObject>, canvas: Canvas, color: String)
}
