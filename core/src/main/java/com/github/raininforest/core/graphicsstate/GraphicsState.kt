package com.github.raininforest.core.graphicsstate

import com.github.raininforest.core.graphicsstate.enums.*
import com.github.raininforest.core.graphicsstate.enums.Units
import com.github.raininforest.core.Aperture

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GraphicsState {
    //TODO
    val coordinateFormat: CoordinateFormat
    val units: Units

    var currentPoint: Pair<Double, Double>
    var currentAperture: Aperture
    var interpolationState: InterpolationState

    var polarity: Polarity
    var mirroring: Mirroring
    var rotation: Rotation
    var scaling: Scaling

    var regionMode: RegionMode
}