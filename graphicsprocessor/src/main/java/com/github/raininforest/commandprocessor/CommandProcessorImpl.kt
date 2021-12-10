package com.github.raininforest.commandprocessor

import android.graphics.Path
import com.github.raininforest.GraphicsObjectsProvider
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.graphicsobject.GraphicsObjectArc
import com.github.raininforest.graphicsobject.GraphicsObjectLine
import com.github.raininforest.graphicsobject.GraphicsObjectPath
import com.github.raininforest.graphicsobject.configobjects.*
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.api.graphicsstate.GraphicsState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.StandardCircleTemplate
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.StandardObroundTemplate
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.StandardPolygonTemplate
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.StandardRectangleTemplate

class CommandProcessorImpl : CommandProcessor, GraphicsObjectsProvider {

    private var currentPath: Path? = null
    private var currentCircleApertureSizeForDrawing: Double =
        GraphicsStateImpl.DEFAULT_APERTURE_DIA

    private val _data: MutableList<GraphicsObject> = mutableListOf()
    override val data: List<GraphicsObject>
        get() = _data

    override val graphicsState: GraphicsState by lazy {
        GraphicsStateImpl { onCircleApertureSet(it) }
    }

    override val apertureDictionary: ApertureDictionary by lazy { ApertureDictionaryImpl() }

    override val templateDictionary: MacroTemplateDictionary by lazy {
        TemplateDictionaryImpl().apply {
            add(StandardCircleTemplate())
            add(StandardObroundTemplate())
            add(StandardPolygonTemplate())
            add(StandardRectangleTemplate())
        }
    }

    override var regionMode: RegionMode = RegionMode.NON_INITIALIZED
        set(value) {
            when (value) {
                RegionMode.REGION_STATEMENT -> startRegion()
                RegionMode.NOT_REGION -> finishRegion()
                else -> {
                    // do nothing
                }
            }
            field = value
        }

    override fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double) {
        _data.add(
            GraphicsObjectLine.create(
                x1 = x1.toFloat(),
                y1 = y1.toFloat(),
                x2 = x2.toFloat(),
                y2 = y2.toFloat()
            )
        )
    }

    override fun drawArc(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    ) {
        _data.add(
            GraphicsObjectArc.create(
                left = left.toFloat(),
                top = bottom.toFloat(), //not top, because of inverted Y axis
                right = right.toFloat(),
                bottom = top.toFloat(),
                startAngle = startAngle.toFloat(),
                sweepAngle = sweepAngle.toFloat()
            )
        )
    }

    override fun flashStandardCircle(center: PointD, diameter: Double, holeDiameter: Double) {
        setStandardFlashConfigs()
        currentPath?.addCircle(
            center.x.toFloat(),
            center.y.toFloat(),
            (diameter / 2).toFloat(),
            Path.Direction.CW
        )
        if (holeDiameter != 0.0) {
            currentPath?.addCircle(
                center.x.toFloat(),
                center.y.toFloat(),
                (holeDiameter / 2).toFloat(),
                Path.Direction.CW
            )
        }
        resetStandardFlashConfigs()
    }

    override fun flashStandardRect(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        center: PointD,
        holeDiameter: Double
    ) {
        setStandardFlashConfigs()
        currentPath?.addRect(
            left.toFloat(),
            top.toFloat(),
            right.toFloat(),
            bottom.toFloat(),
            Path.Direction.CW
        )
        if (holeDiameter != 0.0) {
            currentPath?.addCircle(
                center.x.toFloat(),
                center.y.toFloat(),
                (holeDiameter / 2).toFloat(),
                Path.Direction.CW
            )
        }
        resetStandardFlashConfigs()
    }

    override fun flashStandardObround(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        radius: Double,
        center: PointD,
        holeDiameter: Double
    ) {
        setStandardFlashConfigs()
        currentPath?.addRoundRect(
            left.toFloat(),
            top.toFloat(),
            right.toFloat(),
            bottom.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            Path.Direction.CW
        )
        if (holeDiameter != 0.0) {
            currentPath?.addCircle(
                center.x.toFloat(),
                center.y.toFloat(),
                (holeDiameter / 2).toFloat(),
                Path.Direction.CW
            )
        }
        resetStandardFlashConfigs()
    }

    override fun flashStandardPolygon(points: List<PointD>, center: PointD, holeDiameter: Double) {
        setStandardFlashConfigs()
        val path = currentPath
        path?.let { path ->
            path.moveTo(points[0].x.toFloat(), points[0].y.toFloat())
            for (i in points.indices) {
                path.lineTo(points[i].x.toFloat(), points[i].y.toFloat())
            }
        }
        if (holeDiameter != 0.0) {
            currentPath?.addCircle(
                center.x.toFloat(),
                center.y.toFloat(),
                (holeDiameter / 2).toFloat(),
                Path.Direction.CW
            )
        }
        resetStandardFlashConfigs()
    }

    private fun setStandardFlashConfigs() {
        initNewFlashPath(isEvenOdd = true)
        setStandardFlashPenConfigs()
    }

    private fun initNewFlashPath(isEvenOdd: Boolean) {
        currentPath = Path().apply {
            fillType = if (isEvenOdd) Path.FillType.EVEN_ODD else Path.FillType.WINDING
        }
    }

    private fun setStandardFlashPenConfigs() {
        _data.add(PenThicknessConfig(thickness = 0.0f))
        _data.add(PenFillConfig(isFill = true))
    }

    private fun resetStandardFlashConfigs() {
        addPath()
        resetStandardFlashPenConfigs()
        currentPath = null
    }

    private fun addPath() {
        currentPath?.close()
        val path = currentPath
        path?.let {
            _data.add(GraphicsObjectPath(path))
        }
    }

    private fun resetStandardFlashPenConfigs() {
        _data.add(PenThicknessConfig(thickness = currentCircleApertureSizeForDrawing.toFloat()))
        _data.add(PenFillConfig(isFill = false))
    }

    override fun startMacroFlash() {
        setTemporaryOriginForMacro()
    }

    private fun setTemporaryOriginForMacro() {
        _data.add(
            CanvasOriginConfig(
                origin = PointD(
                    x = graphicsState.currentPoint.x,
                    y = graphicsState.currentPoint.y
                )
            )
        )
    }

    override fun finishMacroFlash() {
        resetOrigin()
    }

    private fun resetOrigin() {
        _data.add(
            CanvasOriginConfig(
                origin = PointD(
                    x = -graphicsState.currentPoint.x,
                    y = -graphicsState.currentPoint.y
                )
            )
        )
    }

    override fun addCirclePrimitive(
        cX: Double,
        cY: Double,
        r: Double,
        exposure: Boolean,
        rotation: Double
    ) {
        setMacroFlashConfigs(exposure, rotation)

        currentPath?.addCircle(cX.toFloat(), cY.toFloat(), r.toFloat(), Path.Direction.CW)
        addPath()

        resetMacroFlashConfigs()
    }

    override fun addRectPrimitive(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        exposure: Boolean,
        rotation: Double
    ) {
        setMacroFlashConfigs(exposure, rotation)
        currentPath?.addRect(
            left.toFloat(),
            bottom.toFloat(), //not top, because of inverted Y axis
            right.toFloat(),
            top.toFloat(),
            Path.Direction.CW
        )
        addPath()
        resetMacroFlashConfigs()
    }

    override fun addOutlinePrimitive(points: List<PointD>, exposure: Boolean, rotation: Double) {
        setMacroFlashConfigs(exposure, rotation)
        val path = currentPath
        path?.let { path ->
            path.moveTo(points[0].x.toFloat(), points[0].y.toFloat())
            for (i in points.indices) {
                path.lineTo(points[i].x.toFloat(), points[i].y.toFloat())
            }
        }
        addPath()
        resetMacroFlashConfigs()
    }

    private fun setMacroFlashConfigs(exposure: Boolean, rotation: Double) {
        initNewFlashPath(isEvenOdd = true)
        setMacroFlashPenConfigs(exposure)
        saveCanvasSettings()
        setCanvasRotation(rotation)
    }

    private fun resetMacroFlashConfigs() {
        restoreCanvasSettings()
        resetMacroFlashPenConfigs()
        currentPath = null
    }

    private fun setMacroFlashPenConfigs(exposure: Boolean) {
        _data.add(PenThicknessConfig(thickness = 0.0f))
        _data.add(PenFillConfig(isFill = true))
        _data.add(PenExposureConfig(exposure = exposure))
    }

    private fun resetMacroFlashPenConfigs() {
        _data.add(PenThicknessConfig(thickness = currentCircleApertureSizeForDrawing.toFloat()))
        _data.add(PenFillConfig(isFill = false))
        _data.add(PenExposureConfig(exposure = true))
    }

    private fun setCanvasRotation(rotation: Double) {
        if (rotation != 0.0) {
            _data.add(CanvasRotationConfig(rotation = rotation.toFloat()))
        }
    }

    private fun saveCanvasSettings() {
        _data.add(CanvasSaveConfig)
    }

    private fun restoreCanvasSettings() {
        _data.add(CanvasRestoreConfig)
    }

    private fun startRegion() {
        setRegionConfigs()
    }

    private fun setRegionConfigs() {
        initNewFlashPath(isEvenOdd = false)
        currentPath?.moveTo(
            graphicsState.currentPoint.x.toFloat(),
            graphicsState.currentPoint.y.toFloat()
        )
        _data.add(PenThicknessConfig(thickness = 0.0f))
        _data.add(PenFillConfig(isFill = true))
    }

    override fun moveTo(x: Double, y: Double) {
        currentPath?.moveTo(x.toFloat(), y.toFloat())
    }

    override fun lineTo(x: Double, y: Double) {
        currentPath?.lineTo(x.toFloat(), y.toFloat())
    }

    override fun arcTo(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        startAngle: Double,
        sweepAngle: Double
    ) {
        currentPath?.arcTo(
            left.toFloat(),
            bottom.toFloat(),
            right.toFloat(),
            top.toFloat(),
            startAngle.toFloat(),
            sweepAngle.toFloat(),
            false
        )
    }

    override fun closeContour() {
        currentPath?.close()
    }

    private fun finishRegion() {
        addPath()
        resetRegionConfigs()
    }

    private fun resetRegionConfigs() {
        _data.add(PenFillConfig(isFill = false))
        _data.add(PenThicknessConfig(thickness = currentCircleApertureSizeForDrawing.toFloat()))
    }

    private fun onCircleApertureSet(diameter: Double) {
        currentCircleApertureSizeForDrawing = diameter
        _data.add(PenThicknessConfig(diameter.toFloat()))
    }
}
