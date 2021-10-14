package com.github.raininforest.syntaxparser.impl.commands.aperturetransformation

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Sets the scaling graphics state parameter.
 *
 * Created by Sergey Velesko on 14.10.2021
 */
data class LSCommand(
    override val lineNumber: Int,
    val scaling: Double
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.scaling = scaling
    }

    internal companion object : MultiStringParsable {

        private val LS_PATTERN = Pattern.compile("^%LS([0-9]*\\.?[0-9]*)\\*%")

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = LS_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val scaleFactor = matcher.group(1).toDouble()
                    return LSCommand(
                        lineNumber = lineIndexHandler.lineNumber,
                        scaling = scaleFactor
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