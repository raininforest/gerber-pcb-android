package com.github.raininforest.syntaxparser


/**
 * Created by Sergey Velesko on 26.09.2021
 */
class LineIndexHandler(private val maxIndex: Int) {
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
