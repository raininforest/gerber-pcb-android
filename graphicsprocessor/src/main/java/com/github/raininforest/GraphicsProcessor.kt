package com.github.raininforest

import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.syntaxparser.api.GerberCommand

interface GraphicsProcessor {
    infix fun process(gerberCommands: List<GerberCommand>): List<GraphicsObject>
}