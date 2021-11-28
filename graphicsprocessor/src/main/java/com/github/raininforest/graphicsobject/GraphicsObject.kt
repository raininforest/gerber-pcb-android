package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import android.graphics.Paint

interface GraphicsObject {
    fun draw(canvas: Canvas, paint: Paint)
}