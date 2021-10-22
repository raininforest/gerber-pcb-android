package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.GraphicsProcessor

/**
 * Standard circle [Aperture]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class CircleAperture(
    override val apertureId: String,
    val diameter: Double,
    val holeDiameter: Double? = null
) : Aperture {

    override fun flash(processor: GraphicsProcessor) {
        val center = processor.graphicsState.currentPoint
        processor.arcTo(
            left = center.x - diameter / 2,
            top = center.y + diameter / 2,
            right = center.x + diameter / 2,
            bottom = center.y - diameter / 2,
            startAngle = 0.0,
            sweepAngle = 360.0
        )
        holeDiameter?.let {
            processor.lineTo(center.x + holeDiameter, center.y)
            processor.arcTo(
                left = center.x - holeDiameter / 2,
                top = center.y + holeDiameter / 2,
                right = center.x + holeDiameter / 2,
                bottom = center.y - holeDiameter / 2,
                startAngle = 0.0,
                sweepAngle = 360.0
            )
            processor.lineTo(center.x + diameter / 2, center.y)
        }

        processor.closeAndFillPath()
    }
}