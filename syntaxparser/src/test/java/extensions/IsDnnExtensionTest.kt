package extensions

import com.github.raininforest.syntaxparser.impl.utils.isDnn
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class IsDnnExtensionTest(private val line: String, private val isDnn: Boolean) {

    @Test
    fun test() {
        Assert.assertEquals(line.isDnn(), isDnn)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("D10*", true),
            arrayOf("D100*", true),
            arrayOf("D11*", true),
            arrayOf("D99", true),
            arrayOf("D12*", true),
            arrayOf("D09*", false),
            arrayOf("D01*", false),
            arrayOf("D02*", false),
            arrayOf("D03*", false),
            arrayOf("D1*", false),
            arrayOf("D2*", false),
            arrayOf("10", false),
        )
    }
}