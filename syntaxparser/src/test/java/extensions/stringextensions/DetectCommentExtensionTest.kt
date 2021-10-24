package extensions.stringextensions

import com.github.raininforest.syntaxparser.impl.utils.detectComment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [detectComment]
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class DetectCommentExtensionTest(private val line: String, private val detected: Boolean) {

    @Test
    fun test() {
        Assert.assertEquals(line.detectComment(), detected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("G04*", true),
            arrayOf("G04 345345 dfg dfg df *", true),
            arrayOf("%AD G04*", false),
            arrayOf("%LM*", false),
            arrayOf("G75%*", false),
            arrayOf("X6D01*", false),
            arrayOf("453645*", false)
        )
    }
}