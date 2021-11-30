package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.CoordinateDataParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException

/**
 * Moves the current point to the ([x], [y])
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class D02Command(
    private val x: Double? = null,
    private val y: Double? = null,
    override val lineNumber: Int
) : DOperationCommand(), GerberCommand {

    override fun perform(processor: CommandProcessor) {
        if (processor.regionMode == RegionMode.REGION) {
            processor.closeContour()
            processor.startContour()
        }
        updateCurrentPoint(processor, x, y)
    }

    internal companion object : CoordinateDataParsable {
        override fun parse(
            currentString: String,
            lineNumber: Int,
            coordinateFormat: CoordinateFormat
        ): GerberCommand {
            val coordinates =
                try {
                    parseCoordinates(
                        currentString,
                        coordinateFormat.integerCount,
                        coordinateFormat.decimalCount
                    )
                } catch (e: Throwable) {
                    throw WrongCommandFormatException(lineNumber, e.localizedMessage)
                }

            return D02Command(
                x = coordinates["X"],
                y = coordinates["Y"],
                lineNumber = lineNumber
            )
        }
    }
}
