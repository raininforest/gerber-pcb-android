package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

import com.github.raininforest.syntaxparser.impl.utils.parseToExpression

/**
 * Defines primitive definition in macro body
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroPrimitiveDefinition(
    val primitiveCode: Int,
    val modifiers: List<MacroExpression>
) : MacroBodyItem {

    companion object {
        fun parsePrimitiveDefinition(s: String): MacroBodyItem {
            return try {
                val values = s.split(",")
                when {
                    s.startsWith("0") -> {
                        MacroPrimitiveDefinition(0, emptyList())
                    }
                    values.size > 1 -> {
                        val primitiveCode = values[0].toInt()
                        val modifiers =
                            values.subList(1, values.size).map { it.trim().parseToExpression() }
                        MacroPrimitiveDefinition(primitiveCode, modifiers)
                    }
                    else -> {
                        throw IllegalStateException("Primitive definition parsing Error! modifiers list size < 2")
                    }
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
