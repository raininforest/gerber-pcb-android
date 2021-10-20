package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.GraphicsProcessor

/**
 * Comment [MacroPrimitive]
 *
 * Created by Sergey Velesko on 20.10.2021
 */
class CommentPrimitive : MacroPrimitive {
    override fun draw(processor: GraphicsProcessor) {
        // ignore
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {
        override fun create(modifiers: List<Double>): MacroPrimitive = CommentPrimitive()
    }
}
