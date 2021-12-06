package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Circle [MacroPrimitive]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class CirclePrimitive(
    private val exposure: Boolean,
    private val diameter: Double,
    private val cX: Double,
    private val cY: Double,
    private val rotation: Double = 0.0
) : MacroPrimitive {

    override fun draw(processor: CommandProcessor) {
        processor.addCirclePrimitive(cX, cY, diameter / 2, exposure, rotation)
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {

        override fun create(modifiers: List<Double>): MacroPrimitive {
            if (modifiers.isNotEmpty()) {
                return CirclePrimitive(
                    exposure = modifiers[0].toInt() != 0,
                    diameter = modifiers[1],
                    cX = modifiers[2],
                    cY = modifiers[3],
                    rotation = if (modifiers.size > 4) modifiers[4] else 0.0
                )
            } else {
                throw IllegalStateException("Circle primitive building error!")
            }
        }
    }
}
