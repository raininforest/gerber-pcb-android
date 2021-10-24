package extensions.stringextensions

import com.github.raininforest.syntaxparser.impl.utils.isDCodeD02
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class IsD02ExtensionTest(private val line: String, private val isD02: Boolean) {
    @Test
    fun test() {
        Assert.assertEquals(line.isDCodeD02(), isD02)
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
            arrayOf("D1*", false),
            arrayOf("D01*", false),
            arrayOf("D2*", true),
            arrayOf("D02*", true),
            arrayOf("D03*", false),
            arrayOf("10", false),
        )
    }
}