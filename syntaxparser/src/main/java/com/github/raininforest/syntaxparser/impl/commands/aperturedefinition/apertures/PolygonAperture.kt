package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.models.PointD
import com.github.raininforest.syntaxparser.impl.utils.toRadians
import kotlin.math.cos
import kotlin.math.sin

/**
 * Standard polygon [Aperture]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class PolygonAperture(
    override val apertureId: String,
    val outerDiameter: Double,
    val vertices: Int,
    val rotation: Double = 0.0,
    val holeDiameter: Double = 0.0
) : Aperture {

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

    override fun flash(processor: CommandProcessor) {
        val center = processor.graphicsState.currentPoint
        processor.flashStandardPolygon(
            points = points.map {
                PointD(it.x + center.x, it.y + center.y)
            },
            center = center,
            holeDiameter = holeDiameter
        )
    }
}