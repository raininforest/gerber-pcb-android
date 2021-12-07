package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import com.github.raininforest.PenConfig
import com.github.raininforest.graphicsobject.GraphicsObject

data class PenFillConfig(
    private val isFill: Boolean
) : GraphicsObject {

    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        if (isFill) {
            penConfig.fillMode()
        } else {
            penConfig.strokeMode()
        }
    }
}
