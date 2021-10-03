package com.github.raininforest.syntaxparser.commands.regionstate

import com.github.raininforest.core.GerberCommand
import com.github.raininforest.core.GraphicsProcessor
import com.github.raininforest.core.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.LineIndexHandler
import com.github.raininforest.syntaxparser.Parsable
import com.github.raininforest.syntaxparser.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Created by Sergey Velesko on 19.09.2021
 */
data class G37Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.regionMode = RegionMode.NO_REGION
    }


    companion object : Parsable {

        private val G37_PATTERN by lazy { Pattern.compile("^G37\\*") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = G37_PATTERN.matcher(stringList[lineIndexHandler.index()])
            try {
                if (matcher.find()) {
                    return G37Command(lineNumber = lineIndexHandler.index())
                } else {
                    throw WrongCommandFormatException(
                        line = lineIndexHandler.index(),
                        command = G37Command::class.java.simpleName
                    )
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }

}
