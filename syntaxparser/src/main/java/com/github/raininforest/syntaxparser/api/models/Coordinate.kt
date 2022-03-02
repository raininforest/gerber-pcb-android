package com.github.raininforest.syntaxparser.api.models

/**
 * Describes single coordinate (x or Y)
 */
data class Coordinate(val type: CoordinateType, val value: Float)

enum class CoordinateType {
    X, Y
}
