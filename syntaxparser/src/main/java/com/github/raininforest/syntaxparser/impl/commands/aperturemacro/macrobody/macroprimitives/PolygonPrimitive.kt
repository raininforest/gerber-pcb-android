package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.impl.utils.toRadians
import kotlin.math.cos
import kotlin.math.sin

/**
 * Polygon [MacroPrimitive]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class PolygonPrimitive(
    private val exposure: Boolean,
    private val vertices: Int,
    private val center: PointD,
    private val outerDiameter: Double,
    private val rotation: Double = 0.0
) : MacroPrimitive {

    private val points: MutableList<PointD>

    init {
        val deltaAngle = 360.0 / vertices
        var initAngle = rotation
        points = mutableListOf()
        for (i in 0 until vertices) {
            points.add(
                PointD(
                    x = (outerDiameter / 2) * cos(initAngle.toRadians()),
                    y = (outerDiameter / 2) * sin(initAngle.toRadians())
                )
            )
            initAngle += deltaAngle
        }
    }

    override fun draw(processor: CommandProcessor) {
        processor.isErase = exposure
        processor.macroRotation = rotation
        processor.drawPath(points)
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {
        override fun create(modifiers: List<Double>): MacroPrimitive {
            if (modifiers.isNotEmpty()) {
                return PolygonPrimitive(
                    exposure = modifiers[0].toInt() != 0,
                    vertices = modifiers[1].toInt(),
                    center = PointD(x = modifiers[2], y = modifiers[3]),
                    outerDiameter = modifiers[4],
                    rotation = if (modifiers.size > 5) modifiers[5] else 0.0
                )
            } else {
                throw IllegalStateException("Circle primitive building error!")
            }
        }
    }
}
