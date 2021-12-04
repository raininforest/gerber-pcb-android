package com.github.raininforest

import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt

class PenConfig(val paint: Paint) {
    var thickness: Float = 0.0f
        set(value) {
            paint.strokeWidth = value
            field = value
        }

    @ColorInt
    private val initColor: Int = paint.color

    fun inverseColor() {
        paint.color = Color.BLACK
    }

    fun resetColor() {
        paint.color = initColor
    }
}
