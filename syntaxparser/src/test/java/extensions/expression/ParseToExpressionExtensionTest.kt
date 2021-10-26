package extensions.expression

import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import com.github.raininforest.syntaxparser.impl.utils.parseToExpression
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.lang.IllegalStateException

/**
 * Test for [parseToExpression]
 *
 * Created by Sergey Velesko on 26.10.2021
 */
@RunWith(Parameterized::class)
class ParseToExpressionExtensionTest(
    private val stringExpression: String,
    private val macroExpression: MacroExpression
) {

    @Test
    fun test() {
        Assert.assertEquals(stringExpression.parseToExpression(), macroExpression)
    }

    @Test(expected = IllegalStateException::class)
    fun `should throw exception`() {
        "$stringExpression*2".parseToExpression()
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                "-2+2",
                MacroExpression(
                    listOf(
                        MacroExpressionOperator(ArithmeticOperation.UNARY_MINUS),
                        MacroNumber(2.0),
                        MacroExpressionOperator(ArithmeticOperation.BINARY_PLUS),
                        MacroNumber(2.0)
                    )
                )
            ),
            arrayOf(
                "(1-$2 )x 34.5",
                MacroExpression(
                    listOf(
                        MacroExpressionOperator(ArithmeticOperation.OPENING_PARENTHESIS),
                        MacroNumber(1.0),
                        MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                        MacroVariable(2),
                        MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS),
                        MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
                        MacroNumber(34.5)
                    )
                )
            ),
            arrayOf(
                "45.4 / 2.45 + $1",
                MacroExpression(
                    listOf(
                        MacroNumber(45.4),
                        MacroExpressionOperator(ArithmeticOperation.DIVIDE),
                        MacroNumber(2.45),
                        MacroExpressionOperator(ArithmeticOperation.BINARY_PLUS),
                        MacroVariable(1)
                    )
                )
            ),
            arrayOf(
                "-0+2-3/ 5x89",
                MacroExpression(
                    listOf(
                        MacroExpressionOperator(ArithmeticOperation.UNARY_MINUS),
                        MacroNumber(0.0),
                        MacroExpressionOperator(ArithmeticOperation.BINARY_PLUS),
                        MacroNumber(2.0),
                        MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                        MacroNumber(3.0),
                        MacroExpressionOperator(ArithmeticOperation.DIVIDE),
                        MacroNumber(5.0),
                        MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
                        MacroNumber(89.0),
                    )
                )
            ),
        )
    }
}