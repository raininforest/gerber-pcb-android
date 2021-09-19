package com.github.raininforest.syntaxparser

import com.github.raininforest.core.GerberCommand

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface SyntaxParser {
    fun parse(stringList: List<String>): List<GerberCommand>
}