package extensions.stringextensions

import com.github.raininforest.syntaxparser.impl.utils.detectDCommand
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [detectDCommand] string extension
 *
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class DetectDCommandExtensionTest(private val line: String, private val detected: Boolean) {

    @Test
    fun test() {
        Assert.assertEquals(line.detectDCommand(), detected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("X235000Y672000*", true),
            arrayOf("X-235000Y-672000D03*", true),
            arrayOf("X-235000D01*", true),
            arrayOf("D03*", true),
            arrayOf("X23500Y0D03*", true),
            arrayOf("Y2D03*", true),
            arrayOf("D02*", true),
            arrayOf("", false),
            arrayOf("453645*", false)
        )
    }
}
