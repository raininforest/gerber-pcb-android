package com.github.raininforest.syntaxparser.impl.commands.inerpolationstate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState

/**
 * Sets interpolation mode graphics state parameter to ‘linear interpolation’
 *
 * Created by Sergey Velesko on 13.10.2021
 */
data class G01Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: GraphicsProcessor) {
        processor.graphicsState.interpolationState = InterpolationState.LINEAR
    }
}