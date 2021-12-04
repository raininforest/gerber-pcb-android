package com.github.raininforest.graphicsobject.configobjects

import android.graphics.Canvas
import android.graphics.Paint
import com.github.raininforest.PenConfig
import com.github.raininforest.graphicsobject.GraphicsObject

data class ConfigFillGraphicsObject(
    private val isFill: Boolean
) : GraphicsObject {

    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        if (isFill) {
            penConfig.paint.style = Paint.Style.FILL_AND_STROKE
        } else {
            penConfig.paint.style = Paint.Style.STROKE
        }
    }
}
