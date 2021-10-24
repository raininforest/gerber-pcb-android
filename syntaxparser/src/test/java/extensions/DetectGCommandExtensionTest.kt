package extensions

import com.github.raininforest.syntaxparser.impl.utils.detectGCommand
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [detectGCommand] string extension
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class DetectGCommandExtensionTest(private val line: String, private val detected: Boolean) {

    @Test
    fun test() {
        Assert.assertEquals(line.detectGCommand(), detected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("G01*", true),
            arrayOf("G02*", true),
            arrayOf("G03*", true),
            arrayOf("G36*", true),
            arrayOf("G37*", true),
            arrayOf("G74*", true),
            arrayOf("G75*", true),
            arrayOf("", false),
            arrayOf("453645*", false)
        )
    }
}