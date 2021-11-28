package com.github.raininforest.graphicsobject

import android.graphics.Canvas
import android.graphics.Paint

data class GraphicsObjectArc(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float
) : GraphicsObject {

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawArc(
            left,
            top,
            right,
            bottom,
            startAngle,
            sweepAngle,
            false,
            paint
        )
    }

    companion object {
        fun create(
            left: Float,
            top: Float,
            right: Float,
            bottom: Float,
            startAngle: Float,
            sweepAngle: Float
        ): GraphicsObject =
            GraphicsObjectArc(left, top, right, bottom, startAngle, sweepAngle)
    }
}