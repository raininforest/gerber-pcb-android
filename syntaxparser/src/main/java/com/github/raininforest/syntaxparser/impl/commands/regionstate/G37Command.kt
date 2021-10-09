package com.github.raininforest.syntaxparser.impl.commands.regionstate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.Parsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Created by Sergey Velesko on 19.09.2021
 */
data class G37Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.regionMode = RegionMode.NO_REGION
    }


    internal companion object : Parsable {

        private val G37_PATTERN by lazy { Pattern.compile("^G37\\*") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = G37_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    return G37Command(lineNumber = lineIndexHandler.lineNumber)
                } else {
                    throw WrongCommandFormatException(line = lineIndexHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }

}
