package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.impl.utils.fromCoordinateToDouble
import java.util.regex.Pattern

/**
 * Base class for operations commands (D01, D02, D03)
 *
 * Created by Sergey Velesko on 19.09.2021
 */
abstract class DOperationCommand {

    protected fun updateCurrentPoint(processor: CommandProcessor, x: Double?, y: Double?) {
        processor.graphicsState.currentPoint =
            PointD(
                x ?: processor.graphicsState.currentPoint.x,
                y ?: processor.graphicsState.currentPoint.y
            )
    }

    internal companion object {
        private val COORDINATE_DATA_PATTERN by lazy { Pattern.compile("([XYIJ])([-+]?\\d+)") }

        fun parseCoordinates(
            line: String,
            numOfInteger: Int,
            numOfDecimal: Int
        ): Map<String, Double> {
            val matcher = COORDINATE_DATA_PATTERN.matcher(line)
            val coordinates = mutableMapOf<String, Double>()
            try {
                while (matcher.find()) {
                    coordinates[matcher.group(1)] =
                        matcher.group(2).fromCoordinateToDouble(numOfInteger, numOfDecimal)
                }
            } catch (e: Exception) {
                throw e
            }

            return coordinates
        }
    }

    enum class DCode {
        D01,
        D02,
        D03
    }
}
