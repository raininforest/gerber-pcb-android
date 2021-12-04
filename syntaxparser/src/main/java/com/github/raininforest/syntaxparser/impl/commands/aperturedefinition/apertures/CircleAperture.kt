package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor

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

        // start
        processor.startFlash(isMacro = false)

        // circle
        processor.addCircle(
            cX = center.x,
            cY = center.y,
            r = diameter / 2
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