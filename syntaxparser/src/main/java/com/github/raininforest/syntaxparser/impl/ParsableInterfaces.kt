package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.syntaxparser.api.GerberCommand

/**
 * Internal interfaces for command factories
 *
 * Created by Sergey Velesko on 26.09.2021
 */
internal interface MultiStringParsable {
    fun parse(
        stringList: List<String>,
        lineIndexHandler: LineIndexHandler,
    ): GerberCommand
}

internal interface CoordinateDataParsable {
    fun parse(
        stringList: List<String>,
        lineIndexHandler: LineIndexHandler,
        numOfInt: Int,
        numOfDec: Int
    ): GerberCommand
}