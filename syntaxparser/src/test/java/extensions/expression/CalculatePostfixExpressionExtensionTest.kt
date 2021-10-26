package extensions.expression

import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import com.github.raininforest.syntaxparser.impl.utils.calculatePostfixExpression
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [calculatePostfixExpression]
 *
 * Created by Sergey Velesko on 26.10.2021
 */
@RunWith(Parameterized::class)
class CalculatePostfixExpressionExtensionTest(
    private val items: List<MacroExpressionItem>,
    private val result: Double
) {

    @Test
    fun test() {
        Assert.assertEquals(items.calculatePostfixExpression(), result, 0.00001)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                listOf(
                    MacroNumber(1.0),
                    MacroNumber(2.0),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroNumber(34.5),
                    MacroExpressionOperator(ArithmeticOperation.MULTIPLY)
                ),
                -34.5
            ),
            arrayOf(
                listOf(
                    MacroNumber(3.0),
                    MacroNumber(2.0),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroNumber(4.5),
                    MacroExpressionOperator(ArithmeticOperation.MULTIPLY)
                ),
                4.5
            ),
            arrayOf(
                listOf(
                    MacroNumber(3.0),
                    MacroExpressionOperator(ArithmeticOperation.UNARY_MINUS),
                    MacroNumber(2.0),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroNumber(4.5),
                    MacroExpressionOperator(ArithmeticOperation.MULTIPLY)
                ),
                -22.5
            )
        )
    }
}