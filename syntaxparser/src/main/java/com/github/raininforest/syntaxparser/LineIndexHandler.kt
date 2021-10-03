package com.github.raininforest.syntaxparser


/**
 * Created by Sergey Velesko on 26.09.2021
 */
class LineIndexHandler(private val maxIndex: Int) {

    init {
        if (maxIndex < 0) {
            throw IllegalStateException("Wrong max line index. It should be >= 0")
        }
        if (maxIndex > Integer.MAX_VALUE) {
            throw IllegalStateException("Wrong max line index. It should be <= 2147483647")
        }
    }

    private var lineNumber: Int = 0

    fun index() = lineNumber

    fun increment() {
        if (lineNumber < maxIndex) {
            lineNumber++
        } else {
            throw IndexOutOfBoundsException("Wrong line index! Index must be < list size")
        }
    }

    fun reset() {
        lineNumber = 0
    }
}
