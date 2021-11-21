package commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LRCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LSCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [LRCommand]
 *
 * Created by Sergey Velesko on 14.10.2021
 */
class LRCommandTest {
    private val listOfCommands = listOf(
        "34534354",
        "%LR0.65*%",
        "%LR128.34*%",
        "%LR-340.5*%",
        "%LRgfrg*%",
        "fdlkgsjdlksdj"
    )

    @Test
    fun `decimal less than 1 test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply { increment() }

        val command = LRCommand.parse(listOfCommands, indexHandler) as LRCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.rotation, 0.65, 0.0001)
    }

    @Test
    fun `decimal more than 1 test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
            }

        val command = LRCommand.parse(listOfCommands, indexHandler) as LRCommand

        Assert.assertEquals(command.lineNumber, 2)
        Assert.assertEquals(command.rotation, 128.34, 0.0001)
    }

    @Test
    fun `negative decimal test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
            }

        val command = LRCommand.parse(listOfCommands, indexHandler) as LRCommand

        Assert.assertEquals(command.lineNumber, 3)
        Assert.assertEquals(command.rotation, -340.5, 0.0001)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format when negative scale`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
                increment()
            }

        LSCommand.parse(listOfCommands, indexHandler)
    }

    @Test
    fun `perform test `() {
        val command: GerberCommand = LRCommand( 140.254, 54)
        val processor = mockk<CommandProcessor>(relaxed = true)
        command.perform(processor)

        verify { processor.graphicsState.rotation = 140.254 }
    }
}