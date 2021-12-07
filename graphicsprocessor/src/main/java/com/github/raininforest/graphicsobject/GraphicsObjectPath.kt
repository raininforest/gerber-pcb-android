package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import android.graphics.Path
import com.github.raininforest.PenConfig

data class GraphicsObjectPath(
    private val path: Path
) : GraphicsObject {

    override fun draw(canvas: Canvas, penConfig: PenConfig) {
        canvas.drawPath(path, penConfig.getPaint())
    }

    companion object {
        fun create(path: Path): GraphicsObject = GraphicsObjectPath(path)
    }
}
