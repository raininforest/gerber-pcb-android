package com.github.raininforest.syntaxparser.impl.commands.regionstate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Begins a region statement.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class G36Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.regionMode = RegionMode.INSIDE_REGION
    }

    internal companion object : MultiStringParsable {

        private val G36_PATTERN by lazy { Pattern.compile("^G36\\*") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = G36_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    return G36Command(lineNumber = lineIndexHandler.lineNumber)
                } else {
                    throw WrongCommandFormatException(line = lineIndexHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
