package com.github.raininforest

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.GerberCommand

/**
 * [GraphicsProcessor] implementation
 */
class GraphicsProcessorImpl(private val commandProcessor: CommandProcessor) : GraphicsProcessor {

    override fun process(gerberCommands: List<GerberCommand>): List<GraphicsObject> {
        gerberCommands.forEach { gerberCommand ->
            gerberCommand.perform(commandProcessor)
        }
        return (commandProcessor as GraphicsObjectsProvider).data
    }
}
