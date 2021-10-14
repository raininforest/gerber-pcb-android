package com.github.raininforest.syntaxparser.impl.commands.aperturetransformation

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Created by Sergey Velesko on 14.10.2021
 */
data class LRCommand(
    override val lineNumber: Int,
    val rotation: Double
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.rotation = rotation
    }

    internal companion object : MultiStringParsable {

        private val LR_PATTERN = Pattern.compile("^%LR([-+]?[0-9]*\\.?[0-9]*)\\*%")

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = LR_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val rotationAngle = matcher.group(1).toDouble()
                    return LRCommand(
                        lineNumber = lineIndexHandler.lineNumber,
                        rotation = rotationAngle
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
