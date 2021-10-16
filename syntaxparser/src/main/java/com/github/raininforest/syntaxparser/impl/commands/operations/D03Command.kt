package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.impl.CoordinateDataParsable
import com.github.raininforest.syntaxparser.impl.LineNumberHandler

/**
 * Performs a flash operation, creating a flash object at ([x], [y])
 *
 * Created by Sergey Velesko on 19.09.2021
 */
class D03Command(
    val x: Double? = null,
    val y: Double? = null,
    override val lineNumber: Int
) : DOperationCommand(), GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.flash(
            x ?: processor.graphicsState.currentPoint.x,
            y ?: processor.graphicsState.currentPoint.y
        )
        updateCurrentPoint(processor, x, y)
    }

    internal companion object : CoordinateDataParsable {
        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler,
            numOfInt: Int,
            numOfDec: Int
        ): GerberCommand {
            val coordinates =
                parseCoordinates(stringList[lineNumberHandler.lineNumber], numOfInt, numOfDec)

            return D03Command(
                x = coordinates["X"],
                y = coordinates["Y"],
                lineNumber = lineNumberHandler.lineNumber
            )
        }
    }
}
