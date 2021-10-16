package com.github.raininforest.syntaxparser.api

import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.api.graphicsstate.GraphicsState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GraphicsProcessor {

    val graphicsState: GraphicsState
    val apertureDictionary: ApertureDictionary
    val macroTemplateDictionary: MacroTemplateDictionary

    var regionMode: RegionMode

    fun drawLine(x: Double, y: Double)

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