package com.github.raininforest.syntaxparser.impl.commands.regionstate

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode

/**
 * Begins a region statement.
 *
 * Created by Sergey Velesko on 19.09.2021
 */
data class G36Command(override val lineNumber: Int) : GerberCommand {

    override fun perform(processor: CommandProcessor) {
        processor.regionMode = RegionMode.REGION_STATEMENT
    }
}
