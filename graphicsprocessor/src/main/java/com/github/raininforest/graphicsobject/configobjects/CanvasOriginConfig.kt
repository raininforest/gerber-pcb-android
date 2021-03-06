package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.PenConfig
import com.github.raininforest.syntaxparser.api.models.PointD

data class CanvasOriginConfig(private val origin: PointD) : GraphicsObject {
    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        canvas.translate(origin.x.toFloat(), origin.y.toFloat())
    }
}
