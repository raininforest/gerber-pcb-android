package com.github.raininforest.syntaxparser.impl.commands.inerpolationstate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState

/**
 * Sets counterclockwise circular interpolation mode
 *
 * Created by Sergey Velesko on 13.10.2021
 */
data class G03Command(override val lineNumber: Int): GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.interpolationState = InterpolationState.COUNTERCLOCKWISE_CIRCULAR
    }
}
