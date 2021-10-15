package commands

import com.github.raininforest.syntaxparser.api.graphicsstate.enums.Units
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.coordinate.FSCommand
import com.github.raininforest.syntaxparser.impl.commands.coordinate.MOCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import org.junit.Assert
import org.junit.Test

/**
 * Test for [MOCommand]
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class MOCommandTest {
    @Test
    fun `ok MM test`() {
        val listOfCommandsWithMOMM = listOf(
            "%G04 34534354",
            "%MOMM*%",
            "%FSLAX24Y24*%"
        )
        val indexHandler = LineNumberHandler(5).apply { increment() }
        val command = MOCommand.parse(listOfCommandsWithMOMM, indexHandler) as MOCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.units, Units.MM)
    }

    @Test
    fun `ok IN test`() {
        val listOfCommands = listOf(
            "%G04 34534354",
            "%MOIN*%",
            "%FSLAX24Y24*%"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        val command = MOCommand.parse(listOfCommands, indexHandler) as MOCommand

        Assert.assertEquals(command.lineNumber, 1)
        Assert.assertEquals(command.units, Units.IN)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test`() {
        val listOfCommands = listOf(
            "34534354",
            "@MOINCH%*",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        FSCommand.parse(listOfCommands, indexHandler) as FSCommand
    }
}
