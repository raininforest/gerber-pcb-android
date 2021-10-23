package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

/**
 * Defines valid arithmetic operations in macro body
 *
 * Created by Sergey Velesko on 16.10.2021
 */
enum class ArithmeticOperation(val priority: Int = 0, val isUnary: Boolean = false) {
    UNARY_PLUS(3, isUnary = true),
    UNARY_MINUS(3, isUnary = true),
    BINARY_PLUS(1),
    BINARY_MINUS(1),
    MULTIPLY(2),
    DIVIDE(2),
    OPENING_PARENTHESIS,
    CLOSING_PARENTHESIS
}
