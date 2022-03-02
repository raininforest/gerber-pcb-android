package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.models.PointD

/**
 * Standard circle [Aperture]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class CircleAperture(
    override val apertureId: String,
    val diameter: Double,
    val holeDiameter: Double = 0.0
) : Aperture {

    override fun flash(processor: CommandProcessor) {
        val center = processor.graphicsState.currentPoint
        processor.flashStandardCircle(center = PointD(center.x, center.y), diameter, holeDiameter)
    }
}