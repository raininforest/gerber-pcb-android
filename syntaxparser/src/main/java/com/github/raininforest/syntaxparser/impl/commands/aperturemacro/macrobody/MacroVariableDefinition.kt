package com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody

import com.github.raininforest.syntaxparser.impl.utils.parseToExpression
import java.util.regex.Pattern

/**
 * Defines variable definition in macro body
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroVariableDefinition(
    val varName: Int,
    val expression: MacroExpression
) : MacroBodyItem {

    companion object {
        @JvmStatic
        val VAR_DEF_PATTERN: Pattern by lazy { Pattern.compile("^\\$([1-9]*)=([.\\$0-9\\-xX\\/\\+\\(\\)]*)") }

        /**
         * Parses variable definition in macro body
         */
        fun parseVarDefinition(s: String): MacroVariableDefinition {
            try {
                val matcher = VAR_DEF_PATTERN.matcher(s.replace("\\s".toRegex(), ""))
                if (matcher.find()) {
                    val varName = matcher.group(1).toInt()
                    val expression = matcher.group(2).parseToExpression()

                    return MacroVariableDefinition(varName, expression)
                } else {
                    throw IllegalStateException("Macro variable definition parsing failed! $s")
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}
