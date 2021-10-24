package extensions.stringextensions

import com.github.raininforest.syntaxparser.impl.utils.isDCodeD01
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class IsD01ExtensionTest(private val line: String, private val isD01: Boolean) {
    @Test
    fun test() {
        Assert.assertEquals(line.isDCodeD01(), isD01)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("D10*", false),
            arrayOf("D100*", false),
            arrayOf("D11*", false),
            arrayOf("D99", false),
            arrayOf("D12*", false),
            arrayOf("D09*", false),
            arrayOf("D01*", true),
            arrayOf("D02*", false),
            arrayOf("D03*", false),
            arrayOf("D1*", true),
            arrayOf("D2*", false),
            arrayOf("10", false),
        )
    }
}