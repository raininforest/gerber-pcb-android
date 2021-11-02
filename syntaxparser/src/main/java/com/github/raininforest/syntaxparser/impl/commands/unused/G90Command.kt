package com.github.raininforest.syntaxparser.impl.commands.unused

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor

/**
 * Unused
 *
 * Created by Sergey Velesko on 02.11.2021
 */
data class G90Command(override val lineNumber: Int): GerberCommand {
    override fun perform(processor: GraphicsProcessor) {
        // ignore
    }
}
