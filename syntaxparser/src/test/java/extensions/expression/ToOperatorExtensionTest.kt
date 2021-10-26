package extensions.expression

import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.ArithmeticOperation
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroExpressionOperator
import com.github.raininforest.syntaxparser.impl.utils.toOperator
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [toOperator]
 *
 * Created by Sergey Velesko on 26.10.2021
 */
@RunWith(Parameterized::class)
class ToOperatorExtensionTest(
    private val char: Char,
    private val isUnary: Boolean,
    private val operator: MacroExpressionOperator
) {

    @Test
    fun test() {
        Assert.assertEquals(char.toOperator(isUnary), operator)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf('-', true, MacroExpressionOperator(ArithmeticOperation.UNARY_MINUS)),
            arrayOf('+', true, MacroExpressionOperator(ArithmeticOperation.UNARY_PLUS)),
            arrayOf('-', false, MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS)),
            arrayOf('+', false, MacroExpressionOperator(ArithmeticOperation.BINARY_PLUS)),
            arrayOf('x', false, MacroExpressionOperator(ArithmeticOperation.MULTIPLY)),
            arrayOf('/', false, MacroExpressionOperator(ArithmeticOperation.DIVIDE)),
            arrayOf('(', false, MacroExpressionOperator(ArithmeticOperation.OPENING_PARENTHESIS)),
            arrayOf(')', false, MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS)),
        )
    }
}