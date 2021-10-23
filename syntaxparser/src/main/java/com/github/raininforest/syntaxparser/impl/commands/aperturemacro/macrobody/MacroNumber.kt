package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

/**
 * Double number in [MacroExpression]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroNumber(override val value: Double) : MacroExpressionOperand
