package com.github.raininforest

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.api.graphicsstate.GraphicsState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode

class CommandProcessorImpl : CommandProcessor {
    override val graphicsState: GraphicsState
        get() = TODO("Not yet implemented")
    override val apertureDictionary: ApertureDictionary
        get() = TODO("Not yet implemented")
    override val templateDictionary: MacroTemplateDictionary
        get() = TODO("Not yet implemented")
    override var regionMode: RegionMode
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isExposure: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var macroRotation: Double
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double) {
        TODO("Not yet implemented")
    }

    override fun drawArc(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun startPath() {
        TODO("Not yet implemented")
    }

    override fun moveTo(x: Double, y: Double) {
        TODO("Not yet implemented")
    }

    override fun lineTo(x: Double, y: Double) {
        TODO("Not yet implemented")
    }

    override fun arcTo(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun addCircle(cX: Double, cY: Double, r: Double) {
        TODO("Not yet implemented")
    }

    override fun addRect(left: Double, top: Double, right: Double, bottom: Double) {
        TODO("Not yet implemented")
    }

    override fun addRoundedRect(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        radius: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun closeAndFillPath() {
        TODO("Not yet implemented")
    }

    override fun drawPath(points: List<PointD>) {
        TODO("Not yet implemented")
    }

    override fun closeContourAndStartNewOne() {
        TODO("Not yet implemented")
    }

    override fun finishDrawing() {
        TODO("Not yet implemented")
    }
}