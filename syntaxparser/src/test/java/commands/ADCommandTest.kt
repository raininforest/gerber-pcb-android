package commands

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.ADCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.MacroTemplate
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [ADCommand]
 *
 * Created by Sergey Velesko on 30.10.2021
 */
class ADCommandTest {
    val command = ADCommand(
        apertureId = "11",
        apertureTemplateName = "Box",
        parameters = listOf(1.0, 1.0),
        lineNumber = 0
    )

    @Test
    fun `perform test`() {
        val graphicsProcessor = mockk<CommandProcessor>(relaxed = true)
        val template = mockk<MacroTemplate>(relaxed = true)
        val aperture = mockk<Aperture>()

        every { graphicsProcessor.templateDictionary.get(command.apertureTemplateName) } returns template
        every { template.buildAperture(command.apertureId, command.parameters) } returns aperture

        command.perform(graphicsProcessor)

        verify { graphicsProcessor.templateDictionary.get(command.apertureTemplateName) }
        verify { template.buildAperture(command.apertureId, command.parameters) }
        verify { graphicsProcessor.apertureDictionary.add(aperture) }
    }

    @Test
    fun `parsing test`() {
        val strCommand = listOf("%ADD15CIRC,45X5*%")

        val command = ADCommand(
            apertureId = "15",
            apertureTemplateName = "CIRC",
            parameters = listOf(45.0, 5.0),
            lineNumber = 0
        )

        Assert.assertEquals(ADCommand.parse(strCommand, LineNumberHandler(0)), command)
    }
}