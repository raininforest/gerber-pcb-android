package com.github.raininforest.syntaxparser.commands.regionstate

import com.github.raininforest.core.GerberCommand
import com.github.raininforest.core.GraphicsProcessor
import com.github.raininforest.core.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.LineIndexHandler
import com.github.raininforest.syntaxparser.Parsable
import com.github.raininforest.syntaxparser.exceptions.WrongCommandFormatException
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

    companion object : Parsable {

        private val G36_PATTERN by lazy { Pattern.compile("^G36\\*") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = G36_PATTERN.matcher(stringList[lineIndexHandler.index()])
            try {
                if (matcher.find()) {
                    return G36Command(lineNumber = lineIndexHandler.index())
                } else {
                    throw WrongCommandFormatException(
                        line = lineIndexHandler.index(),
                        command = G36Command::class.java.simpleName
                    )
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
