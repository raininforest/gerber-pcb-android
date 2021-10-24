package extensions.math

import com.github.raininforest.core.PI
import com.github.raininforest.syntaxparser.impl.utils.toRadians
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [toRadians]
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class DegreesToRadiansTest(private val degrees: Double, private val radians: Double) {

    @Test
    fun test() {
        Assert.assertEquals(degrees.toRadians(), radians, 0.00001)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(0.0, 0.0),
            arrayOf(90.0, PI / 2),
            arrayOf(180.0, PI),
            arrayOf(360.0, 2 * PI),
            arrayOf(-180.0, -PI),
            arrayOf(-45.0, -PI / 4),
        )
    }
}
