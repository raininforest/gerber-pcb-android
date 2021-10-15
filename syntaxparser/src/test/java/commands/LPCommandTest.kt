package commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Polarity
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LPCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test fot [LPCommand]
 *
 * Created by Sergey Velesko on 13.10.2021
 */
class LPCommandTest {

    private val listOfCommands = listOf(
        "34534354",
        "%LPD*%",
        "%LPC*%",
        "LP54*%",
        "fdlkgsjdlksdj"
    )

    @Test
    fun `ok dark polarity test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply { increment() }

        val command = LPCommand.parse(listOfCommands, indexHandler) as LPCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.polarity, Polarity.DARK)
    }

    @Test
    fun `ok clear polarity test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
            }

        val command = LPCommand.parse(listOfCommands, indexHandler) as LPCommand

        Assert.assertEquals(command.lineNumber, 2)
        Assert.assertEquals(command.polarity, Polarity.CLEAR)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
            }

        LPCommand.parse(listOfCommands, indexHandler)
    }

    @Test
    fun `perform test polarity dark`() {
        val command: GerberCommand = LPCommand(Polarity.DARK, 546)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)

        verify { processor.graphicsState.polarity = Polarity.DARK }
    }

    @Test
    fun `perform test polarity clear`() {
        val command: GerberCommand = LPCommand(Polarity.CLEAR, 546)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)

        verify { processor.graphicsState.polarity = Polarity.CLEAR }
    }
}