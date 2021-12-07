package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.PenConfig

data class PenExposureConfig(private val exposure: Boolean) : GraphicsObject {
    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        if (exposure) {
            penConfig.drawMode()
        } else {
            penConfig.eraseMode()
        }
    }
}
