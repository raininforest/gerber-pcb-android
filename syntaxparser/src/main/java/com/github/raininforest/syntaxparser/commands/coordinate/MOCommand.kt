package com.github.raininforest.syntaxparser.commands.coordinate

import com.github.raininforest.core.GerberCommand
import com.github.raininforest.core.GraphicsProcessor
import com.github.raininforest.core.graphicsstate.enums.Units
import com.github.raininforest.syntaxparser.LineIndexHandler
import com.github.raininforest.syntaxparser.Parsable
import com.github.raininforest.syntaxparser.exceptions.WrongCommandFormatException
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

    override fun perform(processor: GraphicsProcessor) {
        //ignore
    }

    companion object : Parsable {

        private val MO_PATTERN by lazy { Pattern.compile("^%MO(MM|IN)\\*%") }

        override fun parse(
            stringList: List<String>,
            lineIndexHandler: LineIndexHandler
        ): GerberCommand {
            val matcher = MO_PATTERN.matcher(stringList[lineIndexHandler.index()])
            try {
                if (matcher.find()) {
                    val unitGroup = matcher.group(1)
                    val units = if (unitGroup.equals("MM")) Units.MM else Units.IN
                    return MOCommand(
                        units = units,
                        lineNumber = lineIndexHandler.index()
                    )
                } else {
                    throw WrongCommandFormatException(
                        line = lineIndexHandler.index(),
                        command = MOCommand::class.java.simpleName
                    )
                }
            } catch (e: Throwable) {
                throw e
            }
        }

    }
}
