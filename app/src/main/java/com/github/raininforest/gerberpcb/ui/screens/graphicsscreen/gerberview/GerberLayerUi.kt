package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import com.github.raininforest.GerberImageSizeInfo
import com.github.raininforest.graphicsobject.GraphicsObject

data class GerberLayerUi(
    val data: List<GraphicsObject>,
    val gerberImageSize: GerberImageSizeInfo,
    val color: String
)
