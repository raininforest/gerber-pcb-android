package com.github.raininforest.syntaxparser.impl


/**
 * Created by Sergey Velesko on 26.09.2021
 */
internal class LineNumberHandler(val maxIndex: Int) {

    init {
        if (maxIndex < 0) {
            throw IllegalStateException("Wrong max line index. It should be >= 0")
        }
        if (maxIndex > Integer.MAX_VALUE) {
            throw IllegalStateException("Wrong max line index. It should be <= 2147483647")
        }
    }

    private var _lineNumber: Int = 0

    var lineNumber = _lineNumber
        private set
        get() = _lineNumber

    fun increment() {
        if (_lineNumber < maxIndex) {
            _lineNumber++
        } else {
            throw IndexOutOfBoundsException("Wrong line index! Index must be < list size")
        }
    }

    fun reset() {
        _lineNumber = 0
    }
}