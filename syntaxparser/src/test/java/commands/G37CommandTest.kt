package commands

import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.regionstate.G37Command
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [G37Command]
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class G37CommandTest {
    @Test
    fun `ok test`() {
        val listOfCommands = listOf(
            "34534354",
            "G37*",
            "M02*"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        val command = G37Command.parse(listOfCommands, indexHandler) as G37Command

        Assert.assertEquals(command.lineNumber, 1)
    }

    @Test
    fun `perform test`() {
        val command = G37Command(56)
        val mockedProcessor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor = mockedProcessor)

        verify { mockedProcessor.graphicsState.regionMode = RegionMode.NO_REGION }
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test`() {
        val listOfCommands = listOf(
            "34534354",
            "%G37*",
            "M02*"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        G37Command.parse(listOfCommands, indexHandler) as G37Command
    }
}