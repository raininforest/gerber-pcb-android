package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.PenConfig

data class ConfigExposureGraphicsObject(private val isErase: Boolean) : GraphicsObject {
    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        if (isErase) {
            penConfig.inverseColor()
        } else {
            penConfig.resetColor()
        }
    }
}
