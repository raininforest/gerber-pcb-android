package com.github.raininforest

import com.github.raininforest.commandprocessor.CommandProcessorImpl
import com.github.raininforest.graphicsobject.GraphicsObject
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.GerberCommand

/**
 * [GraphicsProcessor] implementation
 */
class GraphicsProcessorImpl : GraphicsProcessor {

    override fun process(gerberCommands: List<GerberCommand>): List<GraphicsObject> {
        val commandProcessor: CommandProcessor = CommandProcessorImpl()
        gerberCommands.forEach { gerberCommand ->
            gerberCommand.perform(commandProcessor)
        }
        return (commandProcessor as GraphicsObjectsProvider).data
    }
}
