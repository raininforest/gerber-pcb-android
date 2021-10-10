package com.github.raininforest.syntaxparser.impl.commands.coordinate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Units
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * The MO (Mode) command sets the file unit to either metric (mm) or imperial (inch).
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class MOCommand(
    val units: Units,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {}

    internal companion object : MultiStringParsable {

        private val MO_PATTERN by lazy { Pattern.compile("^%MO(MM|IN)\\*%") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = MO_PATTERN.matcher(stringList[lineIndexHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val unitGroup = matcher.group(1)
                    val units = if (unitGroup.equals("MM")) Units.MM else Units.IN
                    return MOCommand(
                        units = units,
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
