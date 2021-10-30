import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [MacroExpression]
 *
 * Created by Sergey Velesko on 30.10.2021
 */
@RunWith(Parameterized::class)
class MacroExpressionTest(
    private val expression: List<MacroExpressionItem>,
    private val variables: Map<Int, Double>,
    private val result: Double
) {

    @Test
    fun test() {
        val macroExpression = MacroExpression(expression)

        Assert.assertEquals(macroExpression.calculate(variables), result, 0.000001)
    }

    companion object {

        private val simpleExpression = listOf(
            MacroExpressionOperator(ArithmeticOperation.OPENING_PARENTHESIS),
            MacroVariable(1),
            MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
            MacroVariable(2),
            MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS),
            MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
            MacroVariable(3),
            MacroExpressionOperator(ArithmeticOperation.BINARY_PLUS),
            MacroNumber(0.0)
        )

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                simpleExpression,
                mapOf(1 to 0.34, 2 to 2.0, 3 to 13.0),
                -21.58
            ),
            arrayOf(
                simpleExpression,
                mapOf(2 to 2.0, 3 to 13.0),
                -26.0
            ),
            arrayOf(
                simpleExpression,
                emptyMap<Int, Double>(),
                0.0
            ),
            arrayOf(
                simpleExpression,
                mapOf(1 to -0.34, 2 to 2.0, 3 to 0.0),
                0.0
            ),
            arrayOf(
                simpleExpression,
                mapOf(1 to -1000.0, 2 to -2000.0, 3 to 1.0),
                1000.0
            )
        )
    }
}