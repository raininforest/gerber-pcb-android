package extensions.stringextensions

import com.github.raininforest.syntaxparser.impl.utils.detectExtendedCommand
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [detectExtendedCommand]
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class DetectExtendedCommandExtensionTest(private val line: String, private val detected: Boolean) {

    @Test
    fun test() {
        Assert.assertEquals(line.detectExtendedCommand(), detected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("%MO2*", true),
            arrayOf("%AMBox*", true),
            arrayOf("%AD*", true),
            arrayOf("%LM*", true),
            arrayOf("G75%*", false),
            arrayOf("X6D01*", false),
            arrayOf("453645*", false)
        )
    }
}