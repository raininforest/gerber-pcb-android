package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures.MacroAperture
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroBody
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroPrimitiveDefinition
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroVariableDefinition
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives.*
import com.github.raininforest.syntaxparser.impl.utils.toMapDictionary

/**
 * Macro [ApertureTemplate]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroTemplate(
    override val name: String,
    val macroBody: MacroBody,
) : ApertureTemplate {

    override fun buildAperture(apertureId: String, parameters: List<Double>): Aperture {
        val varDictionary = parameters.toMapDictionary()

        val primitives = mutableListOf<MacroPrimitive>()
        macroBody.items.forEach { item ->
            when (item) {
                is MacroPrimitiveDefinition -> {
                    val modifiers = item.modifiers.map {
                        it.calculate(varDictionary)
                    }
                    primitives.add(
                        when (item.primitiveCode) {
                            0 -> CommentPrimitive.create(modifiers)
                            1 -> CirclePrimitive.create(modifiers)
                            20 -> VectorLinePrimitive.create(modifiers)
                            21 -> CenterLinePrimitive.create(modifiers)
                            4 -> OutlinePrimitive.create(modifiers)
                            5 -> PolygonPrimitive.create(modifiers)
                            7 -> ThermalPrimitive.create(modifiers)
                            else -> throw IllegalStateException("Build aperture from template error! No such primitive code: ${item.primitiveCode}")
                        }
                    )
                }
                is MacroVariableDefinition -> {
                    val varName = item.varName
                    val varValue = item.expression.calculate(varDictionary)

                    varDictionary[varName] = varValue
                }
            }
        }

        return MacroAperture(
            apertureId = apertureId,
            primitives = primitives
        )
    }
}
