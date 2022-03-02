package com.github.raininforest.syntaxparser.api.graphicsstate

import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Units
import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.models.PointD
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.*

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GraphicsState {

    val coordinateFormat: CoordinateFormat
    val units: Units

    var currentPoint: PointD
    var currentAperture: Aperture
    var interpolationState: InterpolationState
    var quadrantMode: QuadrantMode

    var polarity: Polarity
    var mirroring: Mirroring
    var rotation: Double
    var scaling: Double
}