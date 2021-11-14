package extensions.stringextensions

import com.github.raininforest.syntaxparser.impl.commands.operations.DOperationCommand
import com.github.raininforest.syntaxparser.impl.utils.isDCodeD01
import com.github.raininforest.syntaxparser.impl.utils.isDCodeD02
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [isDCodeD02] extension
 *
 * Created by Sergey Velesko on 11.10.2021
 */
@RunWith(Parameterized::class)
class IsD02ExtensionTest(
    private val line: String,
    private val isD02: Boolean,
    private val currentDCode: DOperationCommand.DCode
) {
    @Test
    fun test() {
        Assert.assertEquals(line.isDCodeD02(currentDCode), isD02)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("D10*", false, DOperationCommand.DCode.D01),
            arrayOf("D100*", false, DOperationCommand.DCode.D01),
            arrayOf("D11*", false, DOperationCommand.DCode.D01),
            arrayOf("D99", false, DOperationCommand.DCode.D01),
            arrayOf("D12*", false, DOperationCommand.DCode.D01),
            arrayOf("D09*", false, DOperationCommand.DCode.D01),
            arrayOf("D01*", false, DOperationCommand.DCode.D01),
            arrayOf("D02*", true, DOperationCommand.DCode.D01),
            arrayOf("D03*", false, DOperationCommand.DCode.D01),
            arrayOf("D1*", false, DOperationCommand.DCode.D01),
            arrayOf("D2*", true, DOperationCommand.DCode.D01),
            arrayOf("X10", true, DOperationCommand.DCode.D02),
            arrayOf("X10", false, DOperationCommand.DCode.D03),
            arrayOf("X10", false, DOperationCommand.DCode.D01),
        )
    }
}