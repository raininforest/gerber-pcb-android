package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Standard obround [Aperture]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class ObroundAperture(
    override val apertureId: String,
    val xSize: Double,
    val ySize: Double,
    val holeDiameter: Double = 0.0,
) : Aperture {

    override fun flash(processor: CommandProcessor) {
        val center = processor.graphicsState.currentPoint
        processor.flashStandardObround(
            left = center.x - xSize / 2,
            top = center.y + ySize / 2,
            right = center.x + xSize / 2,
            bottom = center.y - ySize / 2,
            radius = minOf(xSize, ySize),
            center = center,
            holeDiameter = holeDiameter
        )
    }
}
