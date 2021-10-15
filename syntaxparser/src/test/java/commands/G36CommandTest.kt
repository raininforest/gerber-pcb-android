package commands

import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.regionstate.G36Command
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [G36Command]
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class G36CommandTest {
    @Test
    fun `ok test`() {
        val listOfCommands = listOf(
            "34534354",
            "G36*",
            "M02*",
            "G37"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        val command = G36Command.parse(listOfCommands, indexHandler) as G36Command

        Assert.assertEquals(command.lineNumber, 1)
    }

    @Test
    fun `perform test`() {
        val command = G36Command(56)
        val mockedProcessor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor = mockedProcessor)

        verify { mockedProcessor.graphicsState.regionMode = RegionMode.INSIDE_REGION }
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test`() {
        val listOfCommands = listOf(
            "34534354",
            "%G36*",
            "M02*",
            "G37"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        G36Command.parse(listOfCommands, indexHandler) as G36Command
    }
}