package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Center line [MacroPrimitive]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class CenterLinePrimitive(
    private val exposure: Boolean,
    private val width: Double,
    private val height: Double,
    private val centerX: Double,
    private val centerY: Double,
    private val rotation: Double = 0.0
) : MacroPrimitive {

    override fun draw(processor: CommandProcessor) {
        processor.addRectPrimitive(
            left = centerX - width / 2,
            top = centerY + height / 2,
            right = centerX + width / 2,
            bottom = centerY - height / 2,
            exposure,
            rotation
        )
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {

        override fun create(modifiers: List<Double>): MacroPrimitive {
            if (modifiers.isNotEmpty()) {
                return CenterLinePrimitive(
                    exposure = modifiers[0].toInt() != 0,
                    width = modifiers[1],
                    height = modifiers[2],
                    centerX = modifiers[3],
                    centerY = modifiers[4],
                    rotation = if (modifiers.size > 5) modifiers[5] else 0.0
                )
            } else {
                throw IllegalStateException("Circle primitive building error!")
            }
        }
    }
}
