package com.github.raininforest.syntaxparser.impl.commands.aperturetransformation

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Polarity
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Loads polarity (Dark or Clear)
 *
 * Created by Sergey Velesko on 13.10.2021
 */
data class LPCommand(
    val polarity: Polarity,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.polarity = polarity
    }

    internal companion object : MultiStringParsable {

        private val LP_PATTERN = Pattern.compile("^%LP([DC])\\*%")

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = LP_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val polarity = when (matcher.group(1)) {
                        "C" -> Polarity.CLEAR
                        "D" -> Polarity.DARK
                        else -> throw WrongCommandFormatException(line = lineIndexHandler.lineNumber)
                    }
                    return LPCommand(lineNumber = lineIndexHandler.lineNumber, polarity = polarity)
                } else {
                    throw WrongCommandFormatException(line = lineIndexHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
