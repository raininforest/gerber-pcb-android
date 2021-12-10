package com.github.raininforest.syntaxparser.api

import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.api.graphicsstate.GraphicsState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface CommandProcessor {

    val graphicsState: GraphicsState
    val apertureDictionary: ApertureDictionary
    val templateDictionary: MacroTemplateDictionary
    var regionMode: RegionMode

    fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double)

    fun drawArc(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    )

    fun flashStandardCircle(center: PointD, diameter: Double, holeDiameter: Double)

    fun flashStandardRect(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        center: PointD,
        holeDiameter: Double
    )

    fun flashStandardObround(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        radius: Double,
        center: PointD,
        holeDiameter: Double
    )

    fun flashStandardPolygon(points: List<PointD>, center: PointD, holeDiameter: Double)

    fun startMacroFlash()

    fun finishMacroFlash()

    fun addCirclePrimitive(cX: Double, cY: Double, r: Double, exposure: Boolean, rotation: Double)

    fun addRectPrimitive(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        exposure: Boolean,
        rotation: Double
    )

    fun addOutlinePrimitive(points: List<PointD>, exposure: Boolean, rotation: Double)

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

    fun closeContour()
}