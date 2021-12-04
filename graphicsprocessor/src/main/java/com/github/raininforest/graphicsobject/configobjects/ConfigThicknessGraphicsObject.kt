package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.PenConfig

data class ConfigThicknessGraphicsObject(
    private val thickness: Float,
) : GraphicsObject {
    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        penConfig.thickness = thickness
    }
}
