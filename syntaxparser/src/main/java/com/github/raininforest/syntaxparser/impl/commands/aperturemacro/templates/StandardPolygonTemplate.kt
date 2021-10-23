package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures.PolygonAperture
import com.github.raininforest.syntaxparser.impl.utils.valueOrZero

/**
 * Polygon [ApertureTemplate]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class StandardPolygonTemplate(override val name: String = "P") : ApertureTemplate {

    override fun buildAperture(apertureId: String, parameters: List<Double>): Aperture =
        PolygonAperture(
            apertureId = apertureId,
            outerDiameter = parameters.valueOrZero(0),
            vertices = parameters.valueOrZero(1).toInt(),
            rotation = parameters.valueOrZero(3),
            holeDiameter = parameters.valueOrZero(4)
        )
}