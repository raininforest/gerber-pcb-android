package com.github.raininforest.syntaxparser.commands

import com.github.raininforest.core.GerberCommand
import com.github.raininforest.core.GraphicsProcessor
import com.github.raininforest.syntaxparser.LineIndexHandler
import com.github.raininforest.syntaxparser.Parsable
import com.github.raininforest.syntaxparser.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * End of file.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class M02Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.finishDrawing()
    }

    companion object : Parsable {

        private val M02_PATTERN by lazy { Pattern.compile("^M02\\*") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = M02_PATTERN.matcher(stringList[lineIndexHandler.index()])
            try {
                if (matcher.find()) {
                    return M02Command(lineNumber = lineIndexHandler.index())
                } else {
                    throw WrongCommandFormatException(
                        line = lineIndexHandler.index(),
                        command = M02Command::class.java.simpleName
                    )
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
