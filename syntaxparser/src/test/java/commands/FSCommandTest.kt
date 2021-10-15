package commands

import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.coordinate.FSCommand
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [FSCommand]
 *
 * Created by Sergey Velesko on 26.09.2021
 */
class FSCommandTest {

    @Test
    fun `ok test`() {
        val listOfCommands = listOf(
            "34534354",
            "%FSLAX24Y24*%",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        val command = FSCommand.parse(listOfCommands, indexHandler) as FSCommand

        assertEquals(command.lineNumber, 1)
        assertEquals(command.numOfInteger, 2)
        assertEquals(command.numOfDecimal, 4)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test 1`() {
        val listOfCommands = listOf(
            "34534354",
            "SLAX24Y24*%",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        FSCommand.parse(listOfCommands, indexHandler) as FSCommand
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test 2`() {
        val listOfCommands = listOf(
            "34534354",
            "%FSLAX2.34Y24*%",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        FSCommand.parse(listOfCommands, indexHandler) as FSCommand
    }
}
