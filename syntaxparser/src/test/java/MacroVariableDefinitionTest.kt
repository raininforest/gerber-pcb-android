import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalStateException

/**
 * Test for [MacroVariableDefinition]
 *
 * Created by Sergey Velesko on 30.10.2021
 */
class MacroVariableDefinitionTest {

    @Test
    fun test() {
        val stringDef = "$7=($1-$2)x$3"
        val macroVariableDefinition = MacroVariableDefinition.parseVarDefinition(stringDef)

        val macroVar = MacroVariable(7)
        val expressionItems = listOf(
            MacroExpressionOperator(ArithmeticOperation.OPENING_PARENTHESIS),
            MacroVariable(1),
            MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
            MacroVariable(2),
            MacroExpressionOperator(ArithmeticOperation.CLOSING_PARENTHESIS),
            MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
            MacroVariable(3),
        )
        val expression = MacroExpression(expressionItems)

        Assert.assertEquals(macroVariableDefinition.varName, macroVar.name)
        Assert.assertEquals(macroVariableDefinition.expression, expression)
    }

    @Test(expected = IllegalStateException::class)
    fun `fail test`() {
        val stringDef = "\$h7=($1-$2)x$3"
        MacroVariableDefinition.parseVarDefinition(stringDef)
    }
}