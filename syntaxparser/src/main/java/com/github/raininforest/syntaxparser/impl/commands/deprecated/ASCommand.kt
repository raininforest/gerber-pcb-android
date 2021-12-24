package com.github.raininforest.syntaxparser.impl.commands.deprecated

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.GerberCommand

/**
 * Unused
 *
 * Created by Sergey Velesko on 24.12.2021
 */
data class ASCommand(override val lineNumber: Int) : GerberCommand {
    override fun perform(processor: CommandProcessor) {
        //ignore
    }
}