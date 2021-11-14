package com.github.raininforest.syntaxparser.impl


/**
 * Line number handler. Hadles operation with command list index.
 *
 * Created by Sergey Velesko on 26.09.2021
 */
internal class LineNumberHandler(private val maxIndex: Int) {

    init {
        if (maxIndex < 0) {
            throw IllegalStateException("Wrong max line index. It should be >= 0")
        }
        if (maxIndex > Integer.MAX_VALUE) {
            throw IllegalStateException("Wrong max line index. It should be <= 2147483647")
        }
    }

    private var _lineNumber: Int = 0
    private var _isEnd: Boolean = false

    var lineNumber = _lineNumber
        private set
        get() = _lineNumber

    var isEnd: Boolean = false
        private set
        get() = _isEnd

    fun increment() {
        when {
            _lineNumber < maxIndex -> _lineNumber++
            _lineNumber == maxIndex -> _isEnd = true
            else -> throw IndexOutOfBoundsException("Wrong line index! Index must be < list size")
        }
    }

    fun reset() {
        _lineNumber = 0
    }
}
