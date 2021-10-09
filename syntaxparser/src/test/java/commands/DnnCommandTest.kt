package commands

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import com.github.raininforest.syntaxparser.impl.commands.DnnCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [DnnCommand]
 *
 * Created by Sergey Velesko on 09.10.2021
 */
class DnnCommandTest {
    @Test
    fun `ok format test`() {
        val listOfCommands = listOf(
            "34534354",
            "D10*",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineIndexHandler(listOfCommands.size - 1).apply { increment() }
        val command = DnnCommand.parse(listOfCommands, indexHandler) as DnnCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.apertureNumber, "10")
    }

    @Test
    fun `perform test`() {
        val apertureNumber = "122"
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        val aperture = mockk<Aperture>(relaxed = true)
        val apertureDictionary = mockk<ApertureDictionary>(relaxed = true)
        val slot = slot<String>()

        every { processor.apertureDictionary } returns apertureDictionary
        every { apertureDictionary.get(capture(slot)) } returns aperture

        val command = DnnCommand(apertureNumber = apertureNumber, lineNumber = 143)
        command.perform(processor = processor)

        verify { processor.graphicsState setProperty "currentAperture" }
        verify { processor.graphicsState.currentAperture = aperture }
        verify { apertureDictionary.get(apertureNumber) }
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test 1`() {
        val listOfCommands = listOf(
            "34534354*",
            "Dnn*",
            "fdlkgsjdlksdj*"
        )
        val indexHandler = LineIndexHandler(listOfCommands.size - 1).apply { increment() }
        DnnCommand.parse(listOfCommands, indexHandler) as DnnCommand
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test 2`() {
        val listOfCommands = listOf(
            "34534354*",
            "D09*",
            "fdlkgsjdlksdj*"
        )
        val indexHandler = LineIndexHandler(listOfCommands.size - 1).apply { increment() }
        DnnCommand.parse(listOfCommands, indexHandler) as DnnCommand
    }
}
