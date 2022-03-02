package com.github.raininforest

class SizeCalculator {

    var minX: Float = 0.0f
        private set
    var maxX: Float = 0.0f
        private set
    var minY: Float = 0.0f
        private set
    var maxY: Float = 0.0f
        private set

    fun checkX(x: Float) {
        if (x < minX) {
            minX = x
        } else if (x > maxX) {
            maxX = x
        }
    }

    fun checkY(y: Float) {
        if (y < minY) {
            minY = y
        } else if (y > maxY) {
            maxY = y
        }
    }
}
