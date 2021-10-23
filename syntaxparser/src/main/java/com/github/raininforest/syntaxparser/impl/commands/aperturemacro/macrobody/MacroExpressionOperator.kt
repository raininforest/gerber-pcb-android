package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

/**
 * Base interface for any operators in [MacroExpression]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroExpressionOperator(val operation: ArithmeticOperation) : MacroExpressionItem
