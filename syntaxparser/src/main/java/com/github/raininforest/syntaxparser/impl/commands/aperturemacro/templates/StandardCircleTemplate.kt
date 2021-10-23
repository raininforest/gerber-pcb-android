package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures.CircleAperture
import com.github.raininforest.syntaxparser.impl.utils.valueOrZero

/**
 * Circle [ApertureTemplate]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class StandardCircleTemplate(override val name: String = "C") : ApertureTemplate {

    override fun buildAperture(apertureId: String, parameters: List<Double>): Aperture =
        CircleAperture(
            apertureId = apertureId,
            diameter = parameters.valueOrZero(0),
            holeDiameter = parameters.valueOrZero(1)
        )
}
