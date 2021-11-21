package com.github.raininforest.syntaxparser.api

/**
 * Base interface for all types of apertures
 *
 * Created by Sergey Velesko on 19.09.2021
 */
interface Aperture {
    val apertureId: String
    fun flash(processor: CommandProcessor)
}