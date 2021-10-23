package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures.RectangleAperture
import com.github.raininforest.syntaxparser.impl.utils.valueOrZero

/**
 * Rectangle [ApertureTemplate]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class StandardRectangleTemplate(override val name: String) : ApertureTemplate {

    override fun buildAperture(apertureId: String, parameters: List<Double>): Aperture =
        RectangleAperture(
            apertureId = apertureId,
            xSize = parameters.valueOrZero(0),
            ySize = parameters.valueOrZero(1),
            holeDiameter = parameters.valueOrZero(2)
        )
}