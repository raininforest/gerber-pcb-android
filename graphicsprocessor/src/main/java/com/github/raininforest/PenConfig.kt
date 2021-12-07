package com.github.raininforest

import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt

class PenConfig(private val paint: Paint) {
    var thickness: Float = 0.0f
        set(value) {
            paint.strokeWidth = value
            field = value
        }

    @ColorInt
    private val initColor: Int = paint.color

    fun eraseMode() {
        paint.color = Color.BLACK
    }

    fun drawMode() {
        paint.color = initColor
    }

    fun fillMode() {
        paint.style = Paint.Style.FILL
    }

    fun strokeMode() {
        paint.style = Paint.Style.STROKE
    }

    fun getPaint() = paint
}
