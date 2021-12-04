package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import com.github.raininforest.PenConfig

data class GraphicsObjectLine(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float
) : GraphicsObject {

    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        canvas.drawLine(x1, y1, x2, y2, penConfig.paint)
    }

    companion object {
        fun create(x1: Float, y1: Float, x2: Float, y2: Float): GraphicsObject =
            GraphicsObjectLine(x1, y1, x2, y2)
    }
}