package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.github.raininforest.PenConfig
import com.github.raininforest.graphicsobject.GraphicsObject

class GerberRendererImpl : GerberRenderer {

    override fun render(data: List<GraphicsObject>, canvas: Canvas, @ColorInt color: Int, opacity: Float) {
        val paint = createDefaultPaint(color, opacity)
        val penConfig = PenConfig(paint)

        data.forEach { graphicsObject ->
            graphicsObject.draw(canvas, penConfig)
        }
    }

    private fun createDefaultPaint(@ColorInt color: Int, opacity: Float): Paint =
        Paint().apply {
            setColor(color)
            strokeCap = DEFAULT_STROKE_CAP
            style = DEFAULT_STYLE
            alpha = opacity.toALPHA256()
            isAntiAlias = DEFAULT_ANTIALIAS
        }

    private fun Float.toALPHA256(): Int {
        return (this * DEFAULT_ALPHA).toInt()
    }

    companion object {
        private val DEFAULT_STROKE_CAP = Paint.Cap.ROUND
        private val DEFAULT_STYLE = Paint.Style.STROKE
        private const val DEFAULT_ALPHA = 255
        private const val DEFAULT_ANTIALIAS = false
    }
}
