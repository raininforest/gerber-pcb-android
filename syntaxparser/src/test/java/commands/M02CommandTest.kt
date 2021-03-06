package commands

import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.M02Command
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

/**
 * Test for [M02Command]
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class M02CommandTest {

    @Test
    fun `ok test`() {
        val listOfCommands = listOf(
            "34534354",
            "%FSLAX24Y24*%",
            "M02*"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
            }
        val command = M02Command.parse(listOfCommands, indexHandler) as M02Command

        Assert.assertEquals(command.lineNumber, 2)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test`() {
        val listOfCommands = listOf(
            "34534354",
            "%FSLAX2.34Y24*%",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineNumberHandler(listOfCommands.size - 1).apply { increment() }
        M02Command.parse(listOfCommands, indexHandler) as M02Command
    }
}