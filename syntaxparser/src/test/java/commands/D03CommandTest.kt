package commands

import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.PointD
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.commands.operations.D03Command
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [D03Command]
 *
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class D03CommandTest(
    private val line: String,
    private val xVal: Double?,
    private val yVal: Double?,
    private val currentVal: Double
) {

    @Test
    fun `perform test`() {
        val command = D03Command(xVal, yVal, 43)
        val graphicsProcessor = mockk<GraphicsProcessor>(relaxed = true)

        every { graphicsProcessor.graphicsState.currentPoint.x } returns currentVal
        every { graphicsProcessor.graphicsState.currentPoint.y } returns currentVal

        command.perform(graphicsProcessor)

        verify { graphicsProcessor.flash(x = xVal ?: currentVal, y = yVal ?: currentVal) }
        verify {
            graphicsProcessor.graphicsState.currentPoint =
                PointD(xVal ?: currentVal, yVal ?: currentVal)
        }
    }

    @Test
    fun `coordinate parse test`() {
        val coordinateFormat = CoordinateFormat(2, 4)

        val command =
            D03Command.parse(line, 34, coordinateFormat) as D03Command

        Assert.assertEquals(command.x, xVal)
        Assert.assertEquals(command.y, yVal)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("X235000Y672000D03*", 23.5, 67.2, 2.0),
            arrayOf("X-235000Y-672000D03*", -23.5, -67.2, 2.0),
            arrayOf("X-235000D03*", -23.5, null, 2.0),
            arrayOf("D03*", null, null, 2.0),
            arrayOf("X23500Y0D03*", 2.35, 0.0, 2.0),
            arrayOf("X1Y2D03*", 0.0001, 0.0002, 2.0)
        )
    }
}