package commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LSCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [LSCommand]
 *
 * Created by Sergey Velesko on 14.10.2021
 */
class LSCommandTest {
    private val listOfCommands = listOf(
        "34534354",
        "%LS0.6*%",
        "%LS1.34*%",
        "%LS-0.5*%",
        "%LSgfrg*%",
        "fdlkgsjdlksdj"
    )

    @Test
    fun `decimal less than 1 test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply { increment() }

        val command = LSCommand.parse(listOfCommands, indexHandler) as LSCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.scaling, 0.6, 0.0001)
    }

    @Test
    fun `decimal more than 1 test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
            }

        val command = LSCommand.parse(listOfCommands, indexHandler) as LSCommand

        Assert.assertEquals(command.lineNumber, 2)
        Assert.assertEquals(command.scaling, 1.34, 0.0001)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format when negative scale`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
            }

        LSCommand.parse(listOfCommands, indexHandler)
    }

    @Test
    fun `perform test `() {
        val command: GerberCommand = LSCommand(0.254, 54)
        val processor = mockk<CommandProcessor>(relaxed = true)
        command.perform(processor)

        verify { processor.graphicsState.scaling = 0.254 }
    }
}