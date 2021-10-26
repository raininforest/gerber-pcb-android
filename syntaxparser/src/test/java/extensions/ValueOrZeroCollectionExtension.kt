package extensions

import com.github.raininforest.syntaxparser.impl.utils.valueOrZero
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [valueOrZero]
 *
 * Created by Sergey Velesko on 26.10.2021
 */
@RunWith(Parameterized::class)
class ValueOrZeroCollectionExtension(private val index: Int, private val value: Double) {

    @Test
    fun test() {
        Assert.assertEquals(dataList.valueOrZero(index), value, 0.00001)
    }

    companion object {
        private val dataList = listOf(12.4, 0.0, 1.234, -457.3, 78.0)

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf<Any>(0, 12.4),
            arrayOf<Any>(1, 0.0),
            arrayOf<Any>(2, 1.234),
            arrayOf<Any>(3, -457.3),
            arrayOf<Any>(4, 78.0),
            arrayOf<Any>(5, 0.0),
            arrayOf<Any>(6, 0.0),
        )
    }
}
