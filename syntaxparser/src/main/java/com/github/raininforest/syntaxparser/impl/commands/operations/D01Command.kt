package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.CoordinateDataParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import com.github.raininforest.syntaxparser.impl.utils.toDegrees
import kotlin.math.atan2
import kotlin.math.sqrt

/**
 * Performs an interpolation operation, creating a draw or an arc segment.
 * The interpolation state defines which type of segment is created.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class D01Command(
    private val x: Double? = null,
    private val y: Double? = null,
    private val i: Double = 0.0,
    private val j: Double = 0.0,
    override val lineNumber: Int
) : DOperationCommand(), GerberCommand {

    override fun perform(processor: CommandProcessor) {
        when (processor.graphicsState.interpolationState) {
            InterpolationState.LINEAR -> drawLine(processor)
            InterpolationState.CLOCKWISE_CIRCULAR -> drawArc(processor, isClockwise = true)
            InterpolationState.COUNTERCLOCKWISE_CIRCULAR -> drawArc(processor, isClockwise = false)
        }
    }

    private fun drawLine(processor: CommandProcessor) {
        if (processor.regionMode == RegionMode.REGION) {
            processor.lineTo(
                x = x ?: processor.graphicsState.currentPoint.x,
                y = y ?: processor.graphicsState.currentPoint.y,
            )
        } else {
            processor.drawLine(
                x1 = processor.graphicsState.currentPoint.x,
                y1 = processor.graphicsState.currentPoint.y,
                x2 = x ?: processor.graphicsState.currentPoint.x,
                y2 = y ?: processor.graphicsState.currentPoint.y
            )
        }
        updateCurrentPoint(processor, x, y)
    }

    private fun drawArc(processor: CommandProcessor, isClockwise: Boolean) {
        val center = PointD(
            processor.graphicsState.currentPoint.x + i,
            processor.graphicsState.currentPoint.y + j
        )
        val radius = sqrt((i * i) + (j * j))
        val xVal = x ?: processor.graphicsState.currentPoint.x
        val yVal = y ?: processor.graphicsState.currentPoint.y

        val startAngle = atan2(-i, -j).toDegrees()
        val endAngle = atan2(yVal - center.y, xVal - center.x).toDegrees()
        val sweepAngle = calculateSweepAngle(startAngle, endAngle, isClockwise)

        if (processor.regionMode == RegionMode.REGION) {
            processor.arcTo(
                left = center.x - radius,
                top = center.y + radius,
                right = center.x + radius,
                bottom = center.y - radius,
                startAngle = startAngle,
                sweepAngle = sweepAngle
            )
        } else {
            processor.drawArc(
                left = center.x - radius,
                top = center.y + radius,
                right = center.x + radius,
                bottom = center.y - radius,
                startAngle = startAngle,
                sweepAngle = sweepAngle
            )
        }
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

            return D01Command(
                x = coordinates["X"],
                y = coordinates["Y"],
                i = coordinates["I"] ?: 0.0,
                j = coordinates["J"] ?: 0.0,
                lineNumber
            )
        }

        private fun calculateSweepAngle(
            startAngle: Double,
            endAngle: Double,
            isClockwise: Boolean
        ) = when (isClockwise) {
            true -> if (startAngle <= endAngle) {
                endAngle - (startAngle + 360)
            } else {
                endAngle - startAngle
            }
            false -> if (startAngle >= endAngle) {
                (endAngle + 360) - startAngle
            } else {
                endAngle - startAngle
            }
        }
    }
}
