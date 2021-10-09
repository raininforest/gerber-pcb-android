package com.github.raininforest.syntaxparser.api

import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.api.graphicsstate.GraphicsState

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GraphicsProcessor {
    //TODO add aperture- and macro-dictionary
    val graphicsState: GraphicsState
    val apertureDictionary: ApertureDictionary
    val macroTemplateDictionary: MacroTemplateDictionary

    fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double)
    fun drawArc(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    )

    fun flash(x: Double, y: Double)

    fun finishDrawing()
}