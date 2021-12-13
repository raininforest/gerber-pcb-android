package common

import com.github.raininforest.syntaxparser.api.SyntaxParser
import com.github.raininforest.syntaxparser.impl.GerberValidator
import com.github.raininforest.syntaxparser.impl.SyntaxParserImpl
import com.github.raininforest.syntaxparser.impl.exceptions.InvalidGerberException
import com.github.raininforest.syntaxparser.impl.utils.readTestFile
import org.junit.Assert
import org.junit.Test
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.pathString

/**
 * Test for [SyntaxParserImpl]
 *
 * Created by Sergey Velesko on 23.10.2021
 */
class SyntaxParserTest {

    private val gerberValidator = GerberValidator()
    private val syntaxParser: SyntaxParser = SyntaxParserImpl(gerberValidator)
    private val workingDir: Path = Path.of("src/test/test_gerbers")

    @Test
    fun `should parse valid gerbers`() {
        workingDir.listDirectoryEntries().filter{
            !it.pathString.contains("notValidFile")
        }.forEach { path ->
            val list = readTestFile(path.toFile())
            val cmdList = syntaxParser.parse(list)
            Assert.assertTrue(cmdList.isNotEmpty())
        }
    }

    @Test(expected = InvalidGerberException::class)
    fun `should return empty list when parse invalid gerber`() {
        workingDir.listDirectoryEntries().filter{
            it.pathString.contains("notValidFile")
        }.forEach { path ->
            val list = readTestFile(path.toFile())
            val cmdList = syntaxParser.parse(list)
        }
    }
}
