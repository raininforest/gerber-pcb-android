package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

/**
 * Variable in [MacroExpression]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroVariable(val name: Int, override var value: Double = 0.0) : MacroExpressionOperand
