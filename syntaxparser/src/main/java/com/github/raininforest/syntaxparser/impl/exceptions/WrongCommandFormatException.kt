package com.github.raininforest.syntaxparser.impl.exceptions

/**
 * Exception for wrong command format
 *
 * Created by Sergey Velesko on 26.09.2021
 */
data class WrongCommandFormatException(
    private val line: Int,
    override val message: String = "Wrong command format",
) : Throwable()