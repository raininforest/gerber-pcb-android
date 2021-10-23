package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.impl.utils.degreesToRadians
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
    val rotation: Double,
    val holeDiameter: Double
) : Aperture {

    private val points: MutableList<PointD>

    init {
        val deltaAngle = 360.0 / vertices
        var initAngle = rotation
        points = mutableListOf()
        for (i in 0 until vertices) {
            points.add(
                PointD(
                    x = (outerDiameter / 2) * cos(initAngle.degreesToRadians()),
                    y = (outerDiameter / 2) * sin(initAngle.degreesToRadians())
                )
            )
            initAngle += deltaAngle
        }
    }

    override fun flash(processor: GraphicsProcessor) {
        val center = processor.graphicsState.currentPoint

        // start
        processor.startPath()

        // polygon
        processor.drawPath(points)

        // hole
        if (holeDiameter != 0.0) {
            processor.addCircle(
                cX = center.x,
                cY = center.y,
                r = holeDiameter / 2
            )
        }

        // close
        processor.closeAndFillPath()
    }
}
