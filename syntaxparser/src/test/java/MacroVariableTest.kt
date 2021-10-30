import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroVariable
import org.junit.Assert
import org.junit.Test

/**
 * Created by Sergey Velesko on 30.10.2021
 */
class MacroVariableTest {

    @Test
    fun test() {
        val variable = MacroVariable(1)
        Assert.assertEquals(variable.value, 0.0, 0.000001)
    }
}