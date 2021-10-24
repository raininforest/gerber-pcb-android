package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.impl.CoordinateDataParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException

/**
 * Performs a flash operation, creating a flash object at ([x], [y])
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class D03Command(
    val x: Double? = null,
    val y: Double? = null,
    override val lineNumber: Int
) : DOperationCommand(), GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        updateCurrentPoint(processor, x, y)
        processor.graphicsState.currentAperture.flash(processor)
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

            return D03Command(
                x = coordinates["X"],
                y = coordinates["Y"],
                lineNumber = lineNumber
            )
        }
    }
}
