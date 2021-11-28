package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import android.graphics.Paint

data class GraphicsObjectLine(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float
) : GraphicsObject {

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawLine(x1, y1, x2, y2, paint)
    }

    companion object {
        fun create(x1: Float, y1: Float, x2: Float, y2: Float): GraphicsObject =
            GraphicsObjectLine(x1, y1, x2, y2)
    }
}