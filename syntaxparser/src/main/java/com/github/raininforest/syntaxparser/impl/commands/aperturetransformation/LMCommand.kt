package com.github.raininforest.syntaxparser.impl.commands.aperturetransformation

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Mirroring
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Sets the mirroring graphics state parameter
 *
 * Created by Sergey Velesko on 14.10.2021
 */
data class LMCommand(
    val mirroring: Mirroring,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.mirroring = mirroring
    }

    internal companion object : MultiStringParsable {

        private val LM_PATTERN = Pattern.compile("^%LM(XY|Y|X|N)\\*%")

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = LM_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val mirroring = when (matcher.group(1)) {
                        "XY" -> Mirroring.XY
                        "Y" -> Mirroring.Y
                        "X" -> Mirroring.X
                        "N" -> Mirroring.N
                        else -> throw WrongCommandFormatException(line = lineIndexHandler.lineNumber)
                    }
                    return LMCommand(
                        lineNumber = lineIndexHandler.lineNumber,
                        mirroring = mirroring
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