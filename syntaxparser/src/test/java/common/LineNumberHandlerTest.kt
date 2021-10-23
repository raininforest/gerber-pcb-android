package common

import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import org.junit.Assert
import org.junit.Test

/**
 * Test for [LineNumberHandler]
 *
 * Created by Sergey Velesko on 03.10.2021
 */
class LineNumberHandlerTest {
    @Test
    fun `test ok maxIndex`() {
        val maxIndex = 679806
        val indexHandler = LineNumberHandler(maxIndex)
        for (i in 0 until maxIndex) {
            indexHandler.increment()
        }
        Assert.assertEquals(indexHandler.lineNumber, maxIndex)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `test illegal index increment`() {
        val maxIndex = 214748364
        val indexHandler = LineNumberHandler(maxIndex)
        for (i in 0..(maxIndex+1)) {
            indexHandler.increment()
        }
    }

    @Test
    fun `test reset`() {
        val maxIndex = 10
        val indexHandler = LineNumberHandler(maxIndex)
        for (i in 0 until maxIndex) {
            indexHandler.increment()
        }
        indexHandler.reset()
        Assert.assertEquals(indexHandler.lineNumber, 0)
    }

    @Test(expected = IllegalStateException::class)
    fun `test exception when construct`() {
        val wrongIndex = -4
        val indexHandler = LineNumberHandler(wrongIndex)
    }
}