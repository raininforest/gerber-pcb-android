package com.github.raininforest.syntaxparser.impl.commands.coordinate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Units
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * The MO (Mode) command sets the file unit to either metric (mm) or imperial (inch).
 *
 * Created by Sergey Velesko on 03.10.2021
 */
data class MOCommand(
    val units: Units,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: CommandProcessor) {}

    internal companion object : MultiStringParsable {

        @JvmStatic
        val MO_PATTERN: Pattern by lazy { Pattern.compile("^%MO(MM|IN)\\*%") }

        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler
        ): GerberCommand {
            val matcher = MO_PATTERN.matcher(stringList[lineNumberHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val unitGroup = matcher.group(1)
                    val units = if (unitGroup.equals("MM")) Units.MM else Units.IN
                    return MOCommand(
                        units = units,
                        lineNumber = lineNumberHandler.lineNumber
                    )
                } else {
                    throw WrongCommandFormatException(line = lineNumberHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }

    }
}
