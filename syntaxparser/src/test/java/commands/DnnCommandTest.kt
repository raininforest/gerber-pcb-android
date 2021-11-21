package commands

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.DnnCommand
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
        val line = "D10*"
        val lineNumber = 2
        val command = DnnCommand.parse(line, lineNumber) as DnnCommand

        Assert.assertEquals(command.lineNumber, lineNumber)
        Assert.assertEquals(command.apertureNumber, "10")
    }

    @Test
    fun `perform test`() {
        val apertureNumber = "122"
        val processor = mockk<CommandProcessor>(relaxed = true)
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
        val line = "Dnn*"
        val lineNumber = 12
        DnnCommand.parse(line, lineNumber) as DnnCommand
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test 2`() {
        val line = "D09*"
        val lineNumber = 12
        DnnCommand.parse(line, lineNumber) as DnnCommand
    }
}
