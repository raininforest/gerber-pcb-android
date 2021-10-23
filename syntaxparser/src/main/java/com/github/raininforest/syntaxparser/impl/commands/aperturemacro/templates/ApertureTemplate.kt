package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates

import com.github.raininforest.syntaxparser.api.Aperture

/**
 * Base interface for aperture templates ([MacroTemplate] or four standard templates)
 *
 * Created by Sergey Velesko on 16.10.2021
 */
interface ApertureTemplate {
    /**
     * Name of aperture template
     */
    val name: String

    /**
     * Builds [Aperture] with specified [apertureId] and [parameters]
     *
     * @param apertureId Int id of [Aperture]
     * @param parameters list of [Double] aperture attributes
     *
     * @return [Aperture]
     */
    fun buildAperture(apertureId: String, parameters: List<Double>): Aperture
}
