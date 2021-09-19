package com.github.raininforest.core

import com.github.raininforest.core.graphicsstate.GraphicsState

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GerberCommand {
    val lineNumber: String

    fun perform(processor: GraphicsProcessor)
}