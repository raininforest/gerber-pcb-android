package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

/**
 * Base interface for operands in [MacroExpression] ([MacroVariable] or [MacroNumber])
 *
 * Created by Sergey Velesko on 16.10.2021
 */
interface MacroExpressionOperand : MacroExpressionItem {
    val value: Double
}
