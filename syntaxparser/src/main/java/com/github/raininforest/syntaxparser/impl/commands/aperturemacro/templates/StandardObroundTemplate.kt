package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures.ObroundAperture
import com.github.raininforest.syntaxparser.impl.utils.valueOrZero

/**
 * Obround [ApertureTemplate]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class StandardObroundTemplate(override val name: String = "O") : ApertureTemplate {

    override fun buildAperture(apertureId: String, parameters: List<Double>): Aperture =
        ObroundAperture(
            apertureId = apertureId,
            xSize = parameters.valueOrZero(0),
            ySize = parameters.valueOrZero(1),
            holeDiameter = parameters.valueOrZero(2)
        )
}
