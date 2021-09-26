package com.github.raininforest.syntaxparser

import com.github.raininforest.core.GerberCommand

/**
 * The SyntaxParser
 *
 * Created by Sergey Velesko on 19.09.2021
 */
interface SyntaxParser {
    /**
     * Parses lines from gerber file (line by line from [List] of [String]).
     * Gives a [List] of [GerberCommand].
     *
     * @param stringList list of [String] from gerber file
     * @return [List] of [GerberCommand]
     */
    fun parse(stringList: List<String>): List<GerberCommand>
}