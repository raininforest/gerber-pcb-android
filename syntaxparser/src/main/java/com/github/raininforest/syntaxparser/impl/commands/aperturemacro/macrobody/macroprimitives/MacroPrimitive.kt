package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Base interface for macro primitives
 *
 * Created by Sergey Velesko on 16.10.2021
 */
interface MacroPrimitive {
    fun draw(processor: CommandProcessor)

    /**
     * Base interface for [MacroPrimitive] factories
     *
     * Created by Sergey Velesko on 20.10.2021
     */
    interface MacroPrimitiveFactory {
        fun create(modifiers: List<Double>): MacroPrimitive
    }
}
