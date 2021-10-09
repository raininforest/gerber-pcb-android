package common

import com.github.raininforest.syntaxparser.impl.LineIndexHandler
import org.junit.Assert
import org.junit.Test

/**
 * Test for [LineIndexHandler]
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class LineIndexHandlerTest {
    @Test
    fun `test ok maxIndex`() {
        val maxIndex = 679806
        val indexHandler = LineIndexHandler(maxIndex)
        for (i in 0 until maxIndex) {
            indexHandler.increment()
        }
        Assert.assertEquals(indexHandler.index(), maxIndex)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `test illegal index increment`() {
        val maxIndex = 2147483647
        val indexHandler = LineIndexHandler(maxIndex)
        for (i in 0..maxIndex) {
            indexHandler.increment()
        }
    }

    @Test
    fun `test reset`() {
        val maxIndex = 10
        val indexHandler = LineIndexHandler(maxIndex)
        for (i in 0 until maxIndex) {
            indexHandler.increment()
        }
        indexHandler.reset()
        Assert.assertEquals(indexHandler.index(), 0)
    }

    @Test(expected = IllegalStateException::class)
    fun `test exception when construct`() {
        val wrongIndex = -4
        val indexHandler = LineIndexHandler(wrongIndex)
    }
}