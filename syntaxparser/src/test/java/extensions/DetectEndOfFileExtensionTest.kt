package extensions

import com.github.raininforest.syntaxparser.impl.utils.detectEndOfFile
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [detectEndOfFile]
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class DetectEndOfFileExtensionTest(private val line: String, private val detected: Boolean) {

    @Test
    fun test() {
        Assert.assertEquals(line.detectEndOfFile(), detected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("G01*M02*", true),
            arrayOf("M02*", true),
            arrayOf(" M02*", true),
            arrayOf("G74*", false),
            arrayOf("G75*", false),
            arrayOf("", false),
            arrayOf("453645*", false)
        )
    }
}