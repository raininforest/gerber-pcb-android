package com.github.raininforest

import com.github.raininforest.commandprocessor.CommandProcessorImpl
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.models.CoordinateType
import com.github.raininforest.syntaxparser.api.GerberCommand

/**
 * [GraphicsProcessor] implementation
 */
class GraphicsProcessorImpl : GraphicsProcessor {

    override fun process(gerberCommands: List<GerberCommand>): GerberImageResult {
        val sizeCalculator = SizeCalculator()
        val commandProcessor: CommandProcessor = CommandProcessorImpl { coordinate ->
            when (coordinate.type) {
                CoordinateType.X -> { sizeCalculator.checkX(coordinate.value) }
                CoordinateType.Y -> { sizeCalculator.checkY(coordinate.value) }
            }
        }
        gerberCommands.forEach { gerberCommand ->
            gerberCommand.perform(commandProcessor)
        }
        return GerberImageResult(
            graphicsObjects = (commandProcessor as GraphicsObjectsProvider).data,
            gerberImageSizeInfo = GerberImageSizeInfo(
                minX = sizeCalculator.minX,
                maxX = sizeCalculator.maxX,
                minY = sizeCalculator.minY,
                maxY = sizeCalculator.maxY
            )
        )
    }
}
