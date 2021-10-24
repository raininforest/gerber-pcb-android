package extensions

import com.github.raininforest.syntaxparser.impl.utils.toMapDictionary
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Test for [toMapDictionary]
 *
 * Created by Sergey Velesko on 24.10.2021
 */
@RunWith(Parameterized::class)
class ToMapDictionaryExtensionTest(private val list: List<Double>) {

    @Test
    fun `map should contain elements from list`() {
        val map = list.toMapDictionary()
        for (i in list.indices) {
            Assert.assertTrue(map.containsKey(i + 1))
            Assert.assertEquals(map[i + 1], list[i])
        }
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(listOf(23.0, 0.45, 1.345)),
            arrayOf(listOf(0.0)),
            arrayOf(listOf())
        )
    }
}
