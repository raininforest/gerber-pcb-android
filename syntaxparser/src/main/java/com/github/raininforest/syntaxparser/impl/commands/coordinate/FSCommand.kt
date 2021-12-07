package com.github.raininforest.syntaxparser.impl.commands.coordinate

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import com.github.raininforest.syntaxparser.impl.utils.supportMentorFormat
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

    override fun perform(processor: CommandProcessor) {
        processor.graphicsState.coordinateFormat.integerCount = numOfInteger
        processor.graphicsState.coordinateFormat.decimalCount = numOfDecimal
    }

    internal companion object : MultiStringParsable {

        @JvmStatic
        val FS_PATTERN: Pattern by lazy { Pattern.compile("^%FSLAX(\\d)(\\d)Y\\d+\\*%") }

        private const val MIN_INT_COUNT = 3

        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler
        ): GerberCommand {
            val matcher = FS_PATTERN.matcher(stringList[lineNumberHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val integerCount = matcher.group(1).toInt()
                    val decimalCount = matcher.group(2).toInt()
                    return FSCommand(
                        numOfInteger = integerCount.supportMentorFormat(MIN_INT_COUNT),
                        numOfDecimal = decimalCount,
                        lineNumber = lineNumberHandler.lineNumber
                    )
                } else {
                    throw WrongCommandFormatException(line = lineNumberHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
