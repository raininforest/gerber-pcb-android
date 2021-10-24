package extensions

import com.github.raininforest.syntaxparser.impl.utils.isNotOneLineAMCommand
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [isNotOneLineAMCommand]
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class IsNotOneLineCommandExtensionTest(
    private val line: StringBuilder,
    private val detected: Boolean
) {

    @Test
    fun test() {
        Assert.assertEquals(line.isNotOneLineAMCommand(), detected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(StringBuilder("%AMBox*"), true),
            arrayOf(StringBuilder("%AMBox%"), false),
            arrayOf(StringBuilder("%AMBox*435*54%*"), false)
        )
    }
}