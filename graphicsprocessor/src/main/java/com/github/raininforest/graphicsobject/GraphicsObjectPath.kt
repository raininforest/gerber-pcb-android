package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class GraphicsObjectPath(val path: Path) : GraphicsObject {

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    companion object {
        fun create(): GraphicsObject = GraphicsObjectPath(Path())
    }
}
