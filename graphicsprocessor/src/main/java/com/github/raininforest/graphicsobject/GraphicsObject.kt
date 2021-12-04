package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import com.github.raininforest.PenConfig

interface GraphicsObject {
    fun draw(canvas: Canvas, penConfig: PenConfig)
}