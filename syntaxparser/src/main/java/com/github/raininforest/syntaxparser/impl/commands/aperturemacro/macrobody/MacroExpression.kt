package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

import com.github.raininforest.syntaxparser.impl.utils.calculatePostfixExpression
import com.github.raininforest.syntaxparser.impl.utils.infixToPostfixExpression

/**
 * Represents arithmetic expression in macro body.
 * Can be calculated with [calculate] method
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroExpression(val expressionItems: List<MacroExpressionItem>) {

    private val postfixExpression = expressionItems.infixToPostfixExpression()

    /**
     * Calculates [Double] expression value with given [variableValues]
     *
     * @param variableValues values for variables in [MacroExpression]
     * @return [Double] value of [MacroExpression]
     */
    fun calculate(variableValues: Map<Int, Double>): Double {
        postfixExpression
            .filterIsInstance<MacroVariable>()
            .forEach { variable ->
                val valueFromDictionary = variableValues[variable.name]
                valueFromDictionary?.let { value ->
                    variable.value = value
                }
                variable.value = variableValues[variable.name] ?: 0.0
            }
        return postfixExpression.calculatePostfixExpression()
    }
}
