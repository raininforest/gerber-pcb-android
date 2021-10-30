import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import org.junit.Assert
import org.junit.Test

/**
 * Test for [MacroPrimitiveDefinition]
 *
 * Created by Sergey Velesko on 30.10.2021
 */
class MacroPrimitiveDefinitionTest {

    @Test
    fun `test macro primitive definition parsing`() {
        val stringDefinition = "1,1,\$1,\$2-\$3,\$1-\$3,\$4"
        val macroPrimitiveDefinition =
            MacroPrimitiveDefinition.parsePrimitiveDefinition(stringDefinition)

        val primitiveCode = 1
        val modifiers = listOf(
            MacroExpression(listOf(MacroNumber(1.0))),
            MacroExpression(listOf(MacroVariable(1))),
            MacroExpression(
                listOf(
                    MacroVariable(2),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroVariable(3)
                )
            ),
            MacroExpression(
                listOf(
                    MacroVariable(1),
                    MacroExpressionOperator(ArithmeticOperation.BINARY_MINUS),
                    MacroVariable(3)
                )
            ),
            MacroExpression(listOf(MacroVariable(4))),
        )

        Assert.assertEquals(macroPrimitiveDefinition.primitiveCode, primitiveCode)
        Assert.assertEquals(macroPrimitiveDefinition.modifiers, modifiers)
    }

    @Test(expected = IllegalStateException::class)
    fun `should throw IllegalStateException`() {
        val stringDefinition = "1,1,\$1,\$2-\$3,\$1-\$3,\$4*"
        MacroPrimitiveDefinition.parsePrimitiveDefinition(stringDefinition)
    }
}