package com.github.raininforest

import android.graphics.Canvas

interface GraphicsCommand {
    fun perform(canvas: Canvas)
}