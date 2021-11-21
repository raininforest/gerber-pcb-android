package com.github.raininforest.syntaxparser.impl.commands.aperturetransformation

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Sets the scaling graphics state parameter.
 *
 * Created by Sergey Velesko on 14.10.2021
 */
data class LSCommand(
    val scaling: Double,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: CommandProcessor) {
        processor.graphicsState.scaling = scaling
    }

    internal companion object : MultiStringParsable {

        private val LS_PATTERN = Pattern.compile("^%LS([0-9]*\\.?[0-9]*)\\*%")

        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler
        ): GerberCommand {
            val matcher = LS_PATTERN.matcher(stringList[lineNumberHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val scaleFactor = matcher.group(1).toDouble()
                    return LSCommand(
                        lineNumber = lineNumberHandler.lineNumber,
                        scaling = scaleFactor
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