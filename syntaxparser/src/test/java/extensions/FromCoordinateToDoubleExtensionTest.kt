package extensions

import com.github.raininforest.syntaxparser.impl.utils.fromCoordinateToDouble
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for fromCoordinateToDouble string extension
 *
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class FromCoordinateToDoubleExtensionTest(
    private val stringValue: String,
    private val doubleValue: Double,
    private val numOfInt: Int,
    private val numOfDec: Int,
) {

    private val int = 2
    private val dec = 4

    @Test
    fun `ok test`() {
        Assert.assertEquals(
            stringValue.fromCoordinateToDouble(numOfInt, numOfDec),
            doubleValue,
            0.00001
        )
    }

    @Test(expected = NumberFormatException::class)
    fun `wrong format 1`() {
        "$stringValue.".fromCoordinateToDouble(numOfInt, numOfDec)
    }

    @Test(expected = NumberFormatException::class)
    fun `wrong format 2`() {
        ".$stringValue".fromCoordinateToDouble(numOfInt, numOfDec)
    }

    @Test(expected = NumberFormatException::class)
    fun `wrong format 3`() {
        "fgh$stringValue".fromCoordinateToDouble(numOfInt, numOfDec)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("235000", 23.5, 2, 4),
            arrayOf("-6720", -0.672, 2, 4),
            arrayOf("0", 0.0, 2, 2),
            arrayOf("200", 0.2, 2, 3),
            arrayOf("-10", -0.1, 1, 2),
            arrayOf("-0", 0.0, 2, 4)
        )
    }
}