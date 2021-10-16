package com.github.raininforest.syntaxparser.impl.commands.operations

import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.impl.utils.fromCoordinateToDouble
import java.util.regex.Pattern

/**
 * Base class for operations commands (D01, D02, D03)
 *
 * Created by Sergey Velesko on 19.09.2021
 */
abstract class DOperationCommand {

    protected fun updateCurrentPoint(processor: GraphicsProcessor, x: Double?, y: Double?) {
        processor.graphicsState.currentPoint =
            Pair(
                x ?: processor.graphicsState.currentPoint.first,
                y ?: processor.graphicsState.currentPoint.second
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
}