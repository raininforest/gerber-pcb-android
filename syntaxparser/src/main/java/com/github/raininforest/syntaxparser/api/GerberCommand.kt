package com.github.raininforest.syntaxparser.api

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GerberCommand {
    val lineNumber: Int

    fun perform(processor: CommandProcessor)
}