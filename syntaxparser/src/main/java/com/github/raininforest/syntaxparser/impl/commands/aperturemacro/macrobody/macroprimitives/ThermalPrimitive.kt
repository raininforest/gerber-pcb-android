package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Created by Sergey Velesko on 20.10.2021
 */
class ThermalPrimitive : MacroPrimitive {

    override fun draw(processor: CommandProcessor) {
        TODO("Not yet implemented")
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {
        override fun create(modifiers: List<Double>): MacroPrimitive {
            TODO("Not yet implemented")
        }
    }
}
