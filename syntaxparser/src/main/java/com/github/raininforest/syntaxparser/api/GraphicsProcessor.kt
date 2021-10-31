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
    val templateDictionary: MacroTemplateDictionary

    var regionMode: RegionMode

    // Macro
    var isExposure: Boolean
    var macroRotation: Double

    fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double)

    fun drawArc(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    )

    fun startPath()
    fun moveTo(x: Double, y: Double)
    fun lineTo(x: Double, y: Double)
    fun arcTo(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    )
    fun addCircle(cX: Double, cY: Double, r: Double)
    fun addRect(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double
    )
    fun addRoundedRect(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        radius: Double
    )
    fun closeAndFillPath()

    fun drawPath(points: List<PointD>)

    fun closeContourAndStartNewOne()

    fun finishDrawing()
}