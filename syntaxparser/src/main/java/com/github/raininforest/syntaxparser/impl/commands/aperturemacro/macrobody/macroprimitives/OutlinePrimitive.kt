package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.PointD

/**
 * Outline [MacroPrimitive]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class OutlinePrimitive(
    private val exposure: Boolean,
    private val vertices: Int,
    private val points: List<PointD>,
    private val rotation: Double = 0.0
) : MacroPrimitive {

    override fun draw(processor: CommandProcessor) {
        processor.isErase = exposure
        processor.macroRotation = rotation
        processor.drawPath(points)
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {

        override fun create(modifiers: List<Double>): MacroPrimitive {
            if (modifiers.isNotEmpty()) {
                val verticesCount = modifiers[1].toInt()
                val points = mutableListOf<PointD>()
                val startIndexOfPoints = 2
                val endIndexOfPoints = (1 + (verticesCount + 1) * 2)
                for (i in startIndexOfPoints..endIndexOfPoints step 2) {
                    points.add(PointD(x = modifiers[i], y = modifiers[i + 1]))
                }
                return OutlinePrimitive(
                    exposure = modifiers[0].toInt() != 0,
                    vertices = modifiers[1].toInt(),
                    points = points,
                    rotation = if (modifiers.size > (verticesCount + 1) * 2 + 2) {
                        modifiers[(verticesCount + 1) * 2 + 2]
                    } else 0.0
                )
            } else {
                throw IllegalStateException("Circle primitive building error!")
            }
        }
    }
}