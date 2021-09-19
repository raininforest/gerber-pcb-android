package com.github.raininforest.core

import com.github.raininforest.core.graphicsstate.GraphicsState

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface GraphicsProcessor {
    //TODO
    val graphicsState: GraphicsState

    fun drawLine()
    fun drawArc()
    fun flash()

    fun comment()
    fun endOfFile()
}