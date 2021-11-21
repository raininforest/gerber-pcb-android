package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Comment [MacroPrimitive]
 *
 * Created by Sergey Velesko on 20.10.2021
 */
class CommentPrimitive : MacroPrimitive {

    override fun draw(processor: CommandProcessor) {
        // ignore
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {
        override fun create(modifiers: List<Double>): MacroPrimitive = CommentPrimitive()
    }
}
