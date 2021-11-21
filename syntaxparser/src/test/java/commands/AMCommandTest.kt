package commands

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.AMCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.*
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.MacroTemplate
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [AMCommand]
 *
 * Created by Sergey Velesko on 30.10.2021
 */
@RunWith(Parameterized::class)
class AMCommandTest(private val commandToParse: List<String>) {

    private val command = AMCommand(
        macroTemplate = MacroTemplate(
            "Box",
            macroBody = MacroBody(
                listOf(
                    MacroVariableDefinition(
                        1,
                        MacroExpression(
                            listOf(
                                MacroNumber(2.0),
                                MacroExpressionOperator(ArithmeticOperation.MULTIPLY),
                                MacroNumber(3.0)
                            )
                        )
                    ),
                    MacroPrimitiveDefinition(
                        primitiveCode = 1,
                        modifiers = listOf(
                            MacroExpression(listOf(MacroNumber(1.0))),
                            MacroExpression(listOf(MacroNumber(1.0))),
                            MacroExpression(listOf(MacroNumber(2.0))),
                            MacroExpression(listOf(MacroNumber(3.0))),
                            MacroExpression(listOf(MacroNumber(4.0))),
                        )
                    )
                )
            )
        ),
        lineNumber = 0
    )

    @Test
    fun `perform test `() {
        val graphicsProcessor = mockk<CommandProcessor>(relaxed = true)
        command.perform(graphicsProcessor)

        verify { graphicsProcessor.templateDictionary.add(command.macroTemplate) }
    }

    @Test
    fun `parsing test`() {
        val parsedCommand =
            AMCommand.parse(commandToParse, LineNumberHandler(commandToParse.size - 1))

        Assert.assertEquals(parsedCommand, command)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                listOf(
                    "%AMBox*",
                    "$1=2x3*",
                    "1,1,1,",
                    "2,3,4*",
                    "%"
                )
            ),
            arrayOf(
                listOf(
                    "%AMBox*$1=2x3*1,1,1,2,3,4*%"
                )
            ),
        )
    }
}