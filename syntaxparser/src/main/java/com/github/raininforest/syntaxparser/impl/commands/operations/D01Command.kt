package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.QuadrantMode
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.CoordinateDataParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import com.github.raininforest.syntaxparser.impl.utils.toDegrees
import kotlin.math.absoluteValue
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
        if (processor.graphicsState.quadrantMode == QuadrantMode.MULTI) {
            drawMultiQuadrantArc(processor, isClockwise)
        } else {
            drawSingleQuadrantArc(processor, isClockwise)
        }
        updateCurrentPoint(processor, x, y)
    }

    private fun drawSingleQuadrantArc(processor: CommandProcessor, isClockwise: Boolean) {
        if (isClockwise) {
            drawSingleQuadrantClockwiseArc(processor)
        } else {
            drawSingleQuadrantCounterClockwiseArc(processor)
        }
    }

    private fun drawSingleQuadrantClockwiseArc(processor: CommandProcessor) {
        val currentX = processor.graphicsState.currentPoint.x
        val currentY = processor.graphicsState.currentPoint.y
        val xVal = x ?: processor.graphicsState.currentPoint.x
        val yVal = y ?: processor.graphicsState.currentPoint.y

        val isFirstQuadrant = (currentX < xVal) && (currentY > yVal)
        val isSecondQuadrant = (currentX < xVal) && (currentY < yVal)
        val isThirdQuadrant = (currentX > xVal) && (currentY < yVal)
        val isFourthQuadrant = (currentX > xVal) && (currentY > yVal)

        val center: PointD
        val startAngle: Double
        val endAngle: Double
        var sweepAngle: Double
        val radius = sqrt((i * i) + (j * j))

        when {
            isFirstQuadrant -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x - i,
                    y = processor.graphicsState.currentPoint.y - j
                )
                startAngle = atan2(j, i).toDegrees()
                endAngle = atan2(yVal - center.y, xVal - center.x).toDegrees()
            }
            isSecondQuadrant -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x + i,
                    y = processor.graphicsState.currentPoint.y - j
                )
                startAngle = 180.0 - atan2(j, i).toDegrees()
                endAngle = 180.0 - atan2(yVal - center.y, -xVal + center.x).toDegrees()
            }
            isThirdQuadrant -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x + i,
                    y = processor.graphicsState.currentPoint.y + j
                )
                startAngle = 180.0 + atan2(j, i).toDegrees()
                endAngle = 180.0 + atan2(-yVal + center.y, -xVal + center.x).toDegrees()
            }
            else -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x - i,
                    y = processor.graphicsState.currentPoint.y + j
                )
                startAngle = -atan2(j, i).toDegrees()
                endAngle = -atan2(-yVal + center.y, xVal - center.x).toDegrees()
            }
        }
        sweepAngle = endAngle - startAngle
        if (sweepAngle.absoluteValue > 90) {
            sweepAngle = if (sweepAngle < 90.0) -90.0 else 90.0
        }

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

    private fun drawSingleQuadrantCounterClockwiseArc(processor: CommandProcessor) {
        val currentX = processor.graphicsState.currentPoint.x
        val currentY = processor.graphicsState.currentPoint.y
        val xVal = x ?: processor.graphicsState.currentPoint.x
        val yVal = y ?: processor.graphicsState.currentPoint.y

        val isFirstQuadrant = (currentX > xVal) && (currentY < yVal)
        val isSecondQuadrant = (currentX > xVal) && (currentY > yVal)
        val isThirdQuadrant = (currentX < xVal) && (currentY > yVal)
        val isFourthQuadrant = (currentX < xVal) && (currentY < yVal)

        val center: PointD
        val startAngle: Double
        val endAngle: Double
        var sweepAngle: Double
        val radius = sqrt((i * i) + (j * j))

        when {
            isFirstQuadrant -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x - i,
                    y = processor.graphicsState.currentPoint.y - j
                )
                startAngle = atan2(j, i).toDegrees()
                endAngle = atan2(yVal - center.y, xVal - center.x).toDegrees()
            }
            isSecondQuadrant -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x + i,
                    y = processor.graphicsState.currentPoint.y - j
                )
                startAngle = 180.0 - atan2(j, i).toDegrees()
                endAngle = 180.0 - atan2(yVal - center.y, -xVal + center.x).toDegrees()
            }
            isThirdQuadrant -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x + i,
                    y = processor.graphicsState.currentPoint.y + j
                )
                startAngle = 180.0 + atan2(j, i).toDegrees()
                endAngle = 180.0 + atan2(-yVal + center.y, -xVal + center.x).toDegrees()
            }
            else -> {
                center = PointD(
                    x = processor.graphicsState.currentPoint.x - i,
                    y = processor.graphicsState.currentPoint.y + j
                )
                startAngle = -atan2(j, i).toDegrees()
                endAngle = -atan2(-yVal + center.y, xVal - center.x).toDegrees()
            }
        }
        sweepAngle = endAngle - startAngle
        if (sweepAngle.absoluteValue > 90) {
            sweepAngle = if (sweepAngle < 90.0) -90.0 else 90.0
        }

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

    private fun drawMultiQuadrantArc(processor: CommandProcessor, isClockwise: Boolean) {
        val center = PointD(
            x = processor.graphicsState.currentPoint.x + i,
            y = processor.graphicsState.currentPoint.y + j
        )
        val radius = sqrt((i * i) + (j * j))
        val xVal = x ?: processor.graphicsState.currentPoint.x
        val yVal = y ?: processor.graphicsState.currentPoint.y

        val startAngle = atan2(-j, -i).toDegrees()
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
