package com.github.raininforest.syntaxparser.commands.coordinate

import com.github.raininforest.core.GerberCommand
import com.github.raininforest.core.GraphicsProcessor
import com.github.raininforest.syntaxparser.LineIndexHandler
import com.github.raininforest.syntaxparser.Parsable
import com.github.raininforest.syntaxparser.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Format Specification (FS)
 *
 * Created by Sergey Velesko on 26.09.2021
 */
data class FSCommand(
    val numOfInteger: Int,
    val nubOfDecimal: Int,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {}

    companion object : Parsable {

        private val FS_PATTERN by lazy { Pattern.compile("^%FSLAX(\\d)(\\d)Y\\d+\\*%") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = FS_PATTERN.matcher(stringList[lineIndexHandler.index()])
            try {
                if (matcher.find()) {
                    val integerCount = matcher.group(1).toInt()
                    val decimalCount = matcher.group(2).toInt()
                    return FSCommand(
                        numOfInteger = integerCount,
                        nubOfDecimal = decimalCount,
                        lineNumber = lineIndexHandler.index()
                    )
                } else {
                    throw WrongCommandFormatException(
                        line = lineIndexHandler.index(),
                        command = FSCommand::class.java.simpleName
                    )
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
