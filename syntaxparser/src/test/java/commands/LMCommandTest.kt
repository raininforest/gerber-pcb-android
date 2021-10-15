package commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Mirroring
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LMCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LPCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [LMCommand]
 *
 * Created by Sergey Velesko on 14.10.2021
 */
class LMCommandTest {
    private val listOfCommands = listOf(
        "34534354",
        "%LMN*%",
        "%LMX*%",
        "%LMY*%",
        "%LMXY*%",
        "fdlkgsjdlksdj"
    )

    @Test
    fun `N mirroring test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply { increment() }

        val command = LMCommand.parse(listOfCommands, indexHandler) as LMCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.mirroring, Mirroring.N)
    }

    @Test
    fun `X mirroring test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
            }

        val command = LMCommand.parse(listOfCommands, indexHandler) as LMCommand

        Assert.assertEquals(command.lineNumber, 2)
        Assert.assertEquals(command.mirroring, Mirroring.X)
    }

    @Test
    fun `Y mirroring test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
            }

        val command = LMCommand.parse(listOfCommands, indexHandler) as LMCommand

        Assert.assertEquals(command.lineNumber, 3)
        Assert.assertEquals(command.mirroring, Mirroring.Y)
    }

    @Test
    fun `XY mirroring test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
                increment()
            }

        val command = LMCommand.parse(listOfCommands, indexHandler) as LMCommand

        Assert.assertEquals(command.lineNumber, 4)
        Assert.assertEquals(command.mirroring, Mirroring.XY)
    }


    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test`() {
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
                increment()
                increment()
                increment()
            }

        LPCommand.parse(listOfCommands, indexHandler)
    }

    @Test
    fun `perform test polarity dark`() {
        val command: GerberCommand = LMCommand(Mirroring.XY, 546)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)

        verify { processor.graphicsState.mirroring = Mirroring.XY }
    }
}