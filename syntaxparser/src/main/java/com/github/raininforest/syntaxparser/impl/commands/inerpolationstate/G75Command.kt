package com.github.raininforest.syntaxparser.impl.commands.inerpolationstate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.QuadrantMode

/**
 * Created by Sergey Velesko on 16.10.2021
 */
data class G75Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: CommandProcessor) {
        processor.graphicsState.quadrantMode = QuadrantMode.MULTI
    }
}