package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.Parsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Sets the current aperture graphics state parameter.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class DnnCommand(
    val apertureNumber: String,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        with(processor) {
            graphicsState.currentAperture = apertureDictionary.get(id = apertureNumber)
        }
    }

    internal companion object : Parsable {

        private val DNN_PATTERN by lazy { Pattern.compile("^D([1-9][0-9]+)") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = DNN_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val apertureInt = matcher.group(1)
                    return DnnCommand(
                        apertureNumber = apertureInt,
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
