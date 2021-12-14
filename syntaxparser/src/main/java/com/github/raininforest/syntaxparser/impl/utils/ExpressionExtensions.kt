package com.github.raininforest.syntaxparser.impl.utils

import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import com.github.raininforest.syntaxparser.impl.exceptions.CalculateExpressionException
import java.lang.IllegalStateException

/**
 * Extensions for expressions in macro body
 *
 * Created by Sergey Velesko on 16.10.2021
 */

/**
 * Converts [Char] to [MacroExpressionOperator]
 *
 * @param isUnary indicates this char should be parsed as unary operator or not
 * @return [MacroExpressionOperator]
 */
internal fun Char.toOperator(isUnary: Boolean = false) =
    when (this) {
        '-' -> {
            if (isUnary) MacroExpressionOperator(ArithmeticOperation.UNARY_MINUS)
            else MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS)
        }
        '+' -> {
            if (isUnary) MacroExpressionOperator(ArithmeticOperation.UNARY_PLUS)
            else MacroExpressionOperator(ArithmeticOperation.BINARY_PLUS)
        }
        '/' -> MacroExpressionOperator(ArithmeticOperation.DIVIDE)
        'x' -> MacroExpressionOperator(ArithmeticOperation.MULTIPLY)
        'X' -> MacroExpressionOperator(ArithmeticOperation.MULTIPLY)
        '(' -> MacroExpressionOperator(ArithmeticOperation.OPENING_PARENTHESIS)
        ')' -> MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS)

        else -> {
            throw IllegalStateException("operation parsing error! $this is not valid operator")
        }
    }

/**
 * Parses string expression to infix [MacroExpression]
 *
 * @return [MacroExpression]
 */
internal fun String.parseToExpression(): MacroExpression {
    val expressionItems: MutableList<MacroExpressionItem> = mutableListOf()
    val string = this.filter { !it.isWhitespace() }
    var charIndex = 0
    while (charIndex < string.length) {
        var currentChar = string[charIndex]

        val isNumberBegins = Character.isDigit(currentChar)
        val isVarBegins = currentChar == '$'
        val isItOperator = (currentChar == '-') ||
                (currentChar == '+') ||
                (currentChar == 'x') ||
                (currentChar == 'X') ||
                (currentChar == '/') ||
                (currentChar == '(') ||
                (currentChar == ')')
        val isPreviousSymbolOperator =
            charIndex > 0 && (expressionItems.last() is MacroExpressionOperator)
        val isPreviousSymbolNotClosingPar = charIndex > 0 && string[charIndex-1] != ')'
        val isItUnaryOperator = (currentChar == '-' || currentChar == '+') &&
                (charIndex == 0 || (isPreviousSymbolOperator && isPreviousSymbolNotClosingPar))

        when {
            isNumberBegins -> {
                val numberBuilder = StringBuilder()
                var isNumberNotEnd = true
                while (isNumberNotEnd) {
                    numberBuilder.append(currentChar)
                    charIndex++
                    if (charIndex < string.length) {
                        currentChar = string[charIndex]
                        isNumberNotEnd = Character.isDigit(currentChar) || (currentChar == '.')
                    } else {
                        isNumberNotEnd = false
                    }
                }
                expressionItems.add(MacroNumber(numberBuilder.toString().toDouble()))
            }
            isVarBegins -> {
                charIndex++
                currentChar = string[charIndex]
                val varNameBuilder = StringBuilder()
                var isVarNotEnd = true
                while (isVarNotEnd) {
                    varNameBuilder.append(currentChar)
                    charIndex++
                    if (charIndex < string.length) {
                        currentChar = string[charIndex]
                        isVarNotEnd = Character.isDigit(currentChar)
                    } else {
                        isVarNotEnd = false
                    }
                }
                expressionItems.add(MacroVariable(varNameBuilder.toString().toInt()))
            }
            isItOperator -> {
                expressionItems.add(currentChar.toOperator(isItUnaryOperator))
                charIndex++
            }
            else -> {
                throw IllegalStateException("Expression parsing error! '$currentChar' is not valid symbol")
            }
        }
    }
    return MacroExpression(expressionItems = expressionItems)
}

/**
 * Converts infix [MacroExpression] to postfix [MacroExpression]
 *
 * @return list of [MacroExpressionItem]
 */
internal fun List<MacroExpressionItem>.infixToPostfixExpression(): List<MacroExpressionItem> {
    val infixExpression = this
    val operationStack: MutableList<MacroExpressionOperator> = mutableListOf()
    val postfixExpression = mutableListOf<MacroExpressionItem>()
    for (item in infixExpression) {
        if (item is MacroExpressionOperand) {
            postfixExpression.add(item)
        } else if (item is MacroExpressionOperator) {
            when (item.operation) {
                ArithmeticOperation.OPENING_PARENTHESIS -> {
                    operationStack.push(item)
                }
                ArithmeticOperation.CLOSING_PARENTHESIS -> {
                    while (operationStack.peek().operation != ArithmeticOperation.OPENING_PARENTHESIS) {
                        postfixExpression.add(operationStack.peek())
                        operationStack.pop()
                    }
                    if (operationStack.isNotEmpty()) {
                        if (operationStack.peek().operation == ArithmeticOperation.OPENING_PARENTHESIS) {
                            operationStack.pop()
                        }
                    }
                }
                else -> {
                    if (operationStack.isNotEmpty()) {
                        if (operationStack.peek().operation.priority >= item.operation.priority) {
                            postfixExpression.add(operationStack.peek())
                            operationStack.pop()
                        }
                    }
                    operationStack.push(item)
                }
            }
        }
    }
    if (operationStack.isNotEmpty()) {
        while (operationStack.isNotEmpty()) {
            postfixExpression.add(operationStack.peek())
            operationStack.pop()
        }
    }

    return postfixExpression
}

/**
 * Calculates postfix [MacroExpressionItem] list
 *
 * @return [Double] result
 */
internal fun List<MacroExpressionItem>.calculatePostfixExpression(): Double {
    val postfixExpression = this
    val operandStack: MutableList<MacroExpressionOperand> = mutableListOf()
    for (item in postfixExpression) {
        when (item) {
            is MacroExpressionOperand -> {
                operandStack.push(item)
            }
            is MacroExpressionOperator -> {
                if (item.operation.isUnary) {
                    val operand = operandStack.peek().value
                    operandStack.pop()
                    val result = when (item.operation) {
                        ArithmeticOperation.UNARY_MINUS -> operand * (-1)
                        ArithmeticOperation.UNARY_PLUS -> operand
                        else -> throw ArithmeticException("Wrong operator ${item.operation}")
                    }
                    operandStack.push(MacroNumber(result))
                } else {
                    val secondOperand = operandStack.peek().value
                    operandStack.pop()
                    val firstOperand = operandStack.peek().value
                    operandStack.pop()
                    val result = when (item.operation) {
                        ArithmeticOperation.BINARY_MINUS -> firstOperand - secondOperand
                        ArithmeticOperation.BINARY_PLUS -> firstOperand + secondOperand
                        ArithmeticOperation.MULTIPLY -> firstOperand * secondOperand
                        ArithmeticOperation.DIVIDE -> {
                            if (secondOperand != 0.0) {
                                firstOperand / secondOperand
                            } else  {
                                throw CalculateExpressionException("Divide by zero Error!")
                            }
                        }
                        else -> throw CalculateExpressionException("Wrong operator ${item.operation}")
                    }
                    operandStack.push(MacroNumber(result))
                }
            }
        }
    }

    return operandStack.peek().value
}

// Stack implementation based on MutableList

/**
 * Stack push method for list
 */
internal fun <T> MutableList<T>.push(item: T) = this.add(this.count(), item)

/**
 * Stack pop method for list
 */
internal fun <T> MutableList<T>.pop(): T = this.removeAt(this.count() - 1)

/**
 * Stack peek method for list
 */
internal fun <T> MutableList<T>.peek(): T = this[this.count() - 1]
