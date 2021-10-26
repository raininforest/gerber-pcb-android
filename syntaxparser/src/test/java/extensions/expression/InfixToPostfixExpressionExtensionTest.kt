package extensions.expression

import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import com.github.raininforest.syntaxparser.impl.utils.infixToPostfixExpression
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [infixToPostfixExpression]
 *
 * Created by Sergey Velesko on 26.10.2021
 */
@RunWith(Parameterized::class)
class InfixToPostfixExpressionExtensionTest(
    private val infix: List<MacroExpressionItem>,
    private val postfix: List<MacroExpressionItem>
) {

    @Test
    fun test() {
        Assert.assertEquals(infix.infixToPostfixExpression(), postfix)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `should throw IndexOutOfBoundsException`() {
        val wrongList = listOf(
            MacroNumber(1.0),
            MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
            MacroVariable(2),
            MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS),
            MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
            MacroNumber(34.5)
        )

        wrongList.infixToPostfixExpression()
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                listOf(
                    MacroExpressionOperator(ArithmeticOperation.OPENING_PARENTHESIS),
                    MacroNumber(1.0),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroVariable(2),
                    MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS),
                    MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
                    MacroNumber(34.5)
                ),
                listOf(
                    MacroNumber(1.0),
                    MacroVariable(2),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroNumber(34.5),
                    MacroExpressionOperator(ArithmeticOperation.MULTIPLY)
                ),
            ),
            arrayOf(
                listOf(
                    MacroNumber(1.0),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroVariable(2),
                    MacroExpressionOperator(ArithmeticOperation.DIVIDE),
                    MacroNumber(34.5)
                ),
                listOf(
                    MacroNumber(1.0),
                    MacroVariable(2),
                    MacroNumber(34.5),
                    MacroExpressionOperator(ArithmeticOperation.DIVIDE),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                ),
            )
        )
    }
}