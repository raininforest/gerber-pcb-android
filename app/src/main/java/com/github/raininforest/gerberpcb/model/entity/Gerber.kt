package com.github.raininforest.gerberpcb.model.entity

import com.github.raininforest.GerberImageResult
import com.github.raininforest.graphicsobject.GraphicsObject
import java.util.*

/**
 * Created by Sergey Velesko on 31.10.2021
 */
data class Gerber(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val color: String = DEFAULT_COLOR,
    var isVisible: Boolean = false,
    val data: GerberImageResult
) {
    companion object {
        private const val DEFAULT_COLOR = "#ffffff"
    }
}
