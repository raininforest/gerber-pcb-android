package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.SingleStringParsable
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

    internal companion object : SingleStringParsable {

        val DNN_PATTERN: Pattern by lazy { Pattern.compile("^D([1-9][0-9]+)") }

        override fun parse(
            currentString: String,
            lineNumber: Int
        ): GerberCommand {
            val matcher = DNN_PATTERN.matcher(currentString)
            try {
                if (matcher.find()) {
                    val apertureInt = matcher.group(1)
                    return DnnCommand(
                        apertureNumber = apertureInt,
                        lineNumber = lineNumber
                    )
                } else {
                    throw WrongCommandFormatException(line = lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
