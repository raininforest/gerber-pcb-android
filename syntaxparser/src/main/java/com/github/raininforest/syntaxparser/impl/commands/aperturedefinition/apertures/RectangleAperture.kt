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

        // start
        processor.startFlash(isMacro = false)

        // rectangle
        processor.addRect(
            left = center.x - xSize / 2,
            top = center.y + ySize / 2,
            right = center.x + xSize / 2,
            bottom = center.y - ySize / 2,
        )

        // hole
        if (holeDiameter != 0.0) {
            processor.addCircle(
                cX = center.x,
                cY = center.y,
                r = holeDiameter / 2
            )
        }

        // close
        processor.finishFlash(isMacro = false)
    }
}
