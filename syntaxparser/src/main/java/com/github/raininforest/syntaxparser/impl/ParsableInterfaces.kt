package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat

/**
 * Internal interfaces for command factories
 *
 * Created by Sergey Velesko on 26.09.2021
 */
internal interface SingleStringParsable {
    fun parse(currentString: String, lineNumber: Int): GerberCommand
}

internal interface MultiStringParsable {
    fun parse(
        stringList: List<String>,
        lineNumberHandler: LineNumberHandler,
    ): GerberCommand
}

internal interface CoordinateDataParsable {
    fun parse(
        currentString: String,
        lineNumber: Int,
        coordinateFormat: CoordinateFormat
    ): GerberCommand
}