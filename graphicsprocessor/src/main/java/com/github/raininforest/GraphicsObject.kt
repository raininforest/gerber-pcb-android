package com.github.raininforest

import android.graphics.Canvas

interface GraphicsObject {
    infix fun draw(canvas: Canvas)
}