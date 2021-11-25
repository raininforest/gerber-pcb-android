package com.github.raininforest.commandprocessor

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.api.graphicsstate.GraphicsState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Mirroring
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Polarity
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Units
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures.CircleAperture

class GraphicsStateImpl : GraphicsState {

    override val coordinateFormat: CoordinateFormat = DEFAULT_COORDINATE_FORMAT
    override var units: Units = DEFAULT_UNITS
    override var currentAperture: Aperture = CircleAperture("DEFAULT", 0.1)
    override var currentPoint: PointD = DEFAULT_CURRENT_POINT
    override var interpolationState: InterpolationState = DEFAULT_INTERPOLATION_STATE
    override var polarity: Polarity = DEFAULT_POLARITY
    override var mirroring: Mirroring = DEFAULT_MIRRORING
    override var rotation: Double = DEFAULT_ROTATION
    override var scaling: Double = DEFAULT_SCALING

    companion object {
        private const val DEFAULT_INT_SIGN_COUNT = 3
        private const val DEFAULT_DECIMAL_SIGN_COUNT = 6
        private val DEFAULT_COORDINATE_FORMAT = CoordinateFormat(
            DEFAULT_INT_SIGN_COUNT,
            DEFAULT_DECIMAL_SIGN_COUNT
        )
        private val DEFAULT_UNITS = Units.MM
        private val DEFAULT_CURRENT_POINT = PointD(0.0, 0.0)
        private const val DEFAULT_APERTURE_ID = "DEFAULT"
        private const val DEFAULT_APERTURE_DIA = 0.1
        private val DEFAULT_APERTURE = CircleAperture(
            DEFAULT_APERTURE_ID,
            DEFAULT_APERTURE_DIA
        )
        private val DEFAULT_INTERPOLATION_STATE = InterpolationState.LINEAR
        private val DEFAULT_POLARITY = Polarity.DARK
        private val DEFAULT_MIRRORING = Mirroring.N
        private const val DEFAULT_ROTATION = 0.0
        private const val DEFAULT_SCALING = 1.0
    }
}