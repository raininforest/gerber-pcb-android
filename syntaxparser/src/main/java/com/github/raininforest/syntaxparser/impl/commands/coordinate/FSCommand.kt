package com.github.raininforest.syntaxparser.impl.commands.coordinate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Format Specification (FS)
 *
 * Created by Sergey Velesko on 26.09.2021
 */
data class FSCommand(
    val numOfInteger: Int,
    val numOfDecimal: Int,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {}

    internal companion object : MultiStringParsable {

        private val FS_PATTERN by lazy { Pattern.compile("^%FSLAX(\\d)(\\d)Y\\d+\\*%") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = FS_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val integerCount = matcher.group(1).toInt()
                    val decimalCount = matcher.group(2).toInt()
                    return FSCommand(
                        numOfInteger = integerCount,
                        numOfDecimal = decimalCount,
                        lineNumber = lineIndexHandler.lineNumber
                    )
                } else {
                    throw WrongCommandFormatException(line = lineIndexHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
