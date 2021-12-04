package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.PenConfig

data class ConfigRotationGraphicsObject(private val rotation: Float): GraphicsObject {
    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        canvas.rotate(-rotation)
    }
}
