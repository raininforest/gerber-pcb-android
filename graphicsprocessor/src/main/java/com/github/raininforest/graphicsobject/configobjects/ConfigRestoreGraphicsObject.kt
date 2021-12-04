package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.PenConfig

object ConfigRestoreGraphicsObject : GraphicsObject {
    override fun draw(canvas: Canvas, penConfig: PenConfig) = canvas.restore()
}
