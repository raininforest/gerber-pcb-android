package commands

import com.github.raininforest.syntaxparser.LineIndexHandler
import com.github.raininforest.syntaxparser.commands.M02Command
import com.github.raininforest.syntaxparser.exceptions.WrongCommandFormatException
import org.junit.Assert
import org.junit.Test

/**
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
        val indexHandler = LineIndexHandler(listOfCommands.size - 1)
            .apply {
                increment()
                increment()
            }
        val command = M02Command.parse(listOfCommands, indexHandler) as M02Command

        Assert.assertEquals(command.lineNumber, 2)
    }

    @Test(expected = WrongCommandFormatException::class)
    fun `wrong format test 2`() {
        val listOfCommands = listOf(
            "34534354",
            "%FSLAX2.34Y24*%",
            "fdlkgsjdlksdj"
        )
        val indexHandler = LineIndexHandler(listOfCommands.size - 1).apply { increment() }
        M02Command.parse(listOfCommands, indexHandler) as M02Command
    }
}