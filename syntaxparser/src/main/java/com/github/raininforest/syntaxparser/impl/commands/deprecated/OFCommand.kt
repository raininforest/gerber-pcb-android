package com.github.raininforest.syntaxparser.impl.commands.deprecated

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Unused
 *
 * Created by Sergey Velesko on 02.11.2021
 */
data class OFCommand(override val lineNumber: Int) : GerberCommand {
    override fun perform(processor: CommandProcessor) {
        // ignore
    }
}
