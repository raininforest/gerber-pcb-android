package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.github.raininforest.PenConfig
import com.github.raininforest.graphicsobject.GraphicsObject

class GerberRendererImpl : GerberRenderer {

    override fun render(data: List<GraphicsObject>, canvas: Canvas, color: String) {
        val paint = createDefaultPaint(color)
        val penConfig = PenConfig(paint)

        data.forEach { graphicsObject ->
            graphicsObject.draw(canvas, penConfig)
        }
    }

    private fun createDefaultPaint(color: String): Paint =
        Paint().apply {
            setColor(Color.parseColor(color))
            strokeCap = DEFAULT_STROKE_CAP
            style = DEFAULT_STYLE
            alpha = DEFAULT_ALPHA
            isAntiAlias = DEFAULT_ANTIALIAS
        }

    companion object {
        private val DEFAULT_STROKE_CAP = Paint.Cap.ROUND
        private val DEFAULT_STYLE = Paint.Style.STROKE
        private const val DEFAULT_ALPHA = 128
        private const val DEFAULT_ANTIALIAS = false
    }
}
