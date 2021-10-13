package commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G01Command
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G02Command
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G03Command
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

/**
 * Tests for [G01Command], [G02Command], [G03Command]
 *
 * Created by Sergey Velesko on 13.10.2021
 */
class G01G02G03CommandsTest {

    @Test
    fun `G01 should call perform()`() {
        val g01: GerberCommand = G01Command(4)

        val processor = mockk<GraphicsProcessor>(relaxed = true)
        g01.perform(processor)
        verify { processor.graphicsState.interpolationState = InterpolationState.LINEAR }
    }

    @Test
    fun `G02 should call perform()`() {
        val g02: GerberCommand = G02Command(4)

        val processor = mockk<GraphicsProcessor>(relaxed = true)
        g02.perform(processor)
        verify {
            processor.graphicsState.interpolationState = InterpolationState.CLOCKWISE_CIRCULAR
        }
    }

    @Test
    fun `G03 should call perform()`() {
        val g03: GerberCommand = G03Command(4)

        val processor = mockk<GraphicsProcessor>(relaxed = true)
        g03.perform(processor)
        verify {
            processor.graphicsState.interpolationState =
                InterpolationState.COUNTERCLOCKWISE_CIRCULAR
        }
    }
}