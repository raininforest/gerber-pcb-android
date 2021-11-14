package com.github.raininforest.syntaxparser.api

/**
 * The SyntaxParser.
 * Parses lines from gerber file (line by line).
 * Gives a list of gerber commands.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
interface SyntaxParser {
    /**
     * Parses lines from gerber file (line by line from [List] of [String]).
     * Gives a [List] of [GerberCommand].
     *
     * @param stringList list of [String] from gerber file
     * @param name of gerber file
     * @return [List] of [GerberCommand]
     */
    fun parse(stringList: List<String>, name: String = "<empty>"): List<GerberCommand>
}