package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives

import com.github.raininforest.syntaxparser.api.CommandProcessor

/**
 * Vector line [MacroPrimitive]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class VectorLinePrimitive(
    private val exposure: Boolean,
    private val width: Double,
    private val startX: Double,
    private val startY: Double,
    private val endX: Double,
    private val endY: Double,
    private val rotation: Double = 0.0
) : MacroPrimitive {

    override fun draw(processor: CommandProcessor) {
        processor.isErase = exposure
        processor.macroRotation = rotation
        processor.addRect(
            left = startX,
            top = startY + width / 2,
            right = endX,
            bottom = startY - width / 2
        )
    }

    companion object : MacroPrimitive.MacroPrimitiveFactory {

        override fun create(modifiers: List<Double>): MacroPrimitive {
            if (modifiers.isNotEmpty()) {
                return VectorLinePrimitive(
                    exposure = modifiers[0].toInt() != 0,
                    width = modifiers[1],
                    startX = modifiers[2],
                    startY = modifiers[3],
                    endX = modifiers[4],
                    endY = modifiers[5],
                    rotation = if (modifiers.size > 6) modifiers[6] else 0.0
                )
            } else {
                throw IllegalStateException("Circle primitive building error!")
            }
        }
    }
}
