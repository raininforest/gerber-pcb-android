package com.github.raininforest

import com.github.raininforest.graphicsobject.GraphicsObject

data class GerberImageResult(
    val graphicsObjects: List<GraphicsObject>,
    val gerberImageSizeInfo: GerberImageSizeInfo
)
