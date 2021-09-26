package com.github.raininforest.syntaxparser

import com.github.raininforest.core.GerberCommand

/**
 * Created by Sergey Velesko on 26.09.2021
 */
interface Parsable {
    fun parse(stringList: List<String>, lineIndexHandler: LineIndexHandler): GerberCommand
}