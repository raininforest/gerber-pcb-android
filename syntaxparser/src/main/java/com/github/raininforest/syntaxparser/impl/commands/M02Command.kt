package com.github.raininforest.syntaxparser.impl.commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * End of file.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class M02Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: CommandProcessor) {
        // ignore
    }

    internal companion object : MultiStringParsable {

        private val M02_PATTERN by lazy { Pattern.compile("^M02\\*") }

        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler
        ): GerberCommand {
            val matcher = M02_PATTERN.matcher(stringList[lineNumberHandler.lineNumber])
            try {
                if (matcher.find()) {
                    return M02Command(lineNumber = lineNumberHandler.lineNumber)
                } else {
                    throw WrongCommandFormatException(line = lineNumberHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
