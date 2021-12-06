package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Standard rectangle [Aperture]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class RectangleAperture(
    override val apertureId: String,
    val xSize: Double,
    val ySize: Double,
    val holeDiameter: Double = 0.0
) : Aperture {

    override fun flash(processor: CommandProcessor) {
        val center = processor.graphicsState.currentPoint
        processor.flashStandardRect(
            left = center.x - xSize / 2,
            top = center.y + ySize / 2,
            right = center.x + xSize / 2,
            bottom = center.y - ySize / 2,
            center = center,
            holeDiameter = holeDiameter
        )
    }
}
