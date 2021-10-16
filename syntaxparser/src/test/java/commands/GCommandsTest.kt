package commands

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.GraphicsProcessor
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.InterpolationState
import com.github.raininforest.syntaxparser.api.graphicsstate.enums.RegionMode
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G01Command
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G02Command
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G03Command
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.G74Command
import com.github.raininforest.syntaxparser.impl.commands.regionstate.G36Command
import com.github.raininforest.syntaxparser.impl.commands.regionstate.G37Command
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

/**
 * Tests for [G01Command], [G02Command], [G03Command]
 *
 * Created by Sergey Velesko on 13.10.2021
 */
class GCommandsTest {

    @Test
    fun `G01 perform test()`() {
        val command: GerberCommand = G01Command(4)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)
        verify { processor.graphicsState.interpolationState = InterpolationState.LINEAR }
    }

    @Test
    fun `G02 perform test()`() {
        val command: GerberCommand = G02Command(4)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)
        verify {
            processor.graphicsState.interpolationState = InterpolationState.CLOCKWISE_CIRCULAR
        }
    }

    @Test
    fun `G03 perform test()`() {
        val command = G03Command(4)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)
        verify {
            processor.graphicsState.interpolationState =
                InterpolationState.COUNTERCLOCKWISE_CIRCULAR
        }
    }

    @Test
    fun `G36 perform test`() {
        val command = G36Command(56)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)
        verify { processor.regionMode = RegionMode.REGION }
    }

    @Test
    fun `G37 perform test`() {
        val command = G37Command(56)
        val processor = mockk<GraphicsProcessor>(relaxed = true)
        command.perform(processor)
        verify { processor.regionMode = RegionMode.NO_REGION }
    }
}