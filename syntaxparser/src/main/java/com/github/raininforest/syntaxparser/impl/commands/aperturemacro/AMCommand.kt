package com.github.raininforest.syntaxparser.impl.commands.aperturemacro

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroBody
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroBodyItem
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroPrimitiveDefinition
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.MacroVariableDefinition
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.MacroTemplate
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import com.github.raininforest.syntaxparser.impl.utils.isNotOneLineAMCommand
import java.util.regex.Pattern

/**
 * Creates a macro aperture template and adds it to the aperture template dictionary
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class AMCommand(
    val macroTemplate: MacroTemplate,
    override val lineNumber: Int
) : GerberCommand {

    override fun perform(processor: CommandProcessor) {
        processor.templateDictionary.add(macroTemplate)
    }

    internal companion object : MultiStringParsable {

        @JvmStatic
        val AM_PATTERN: Pattern by lazy { Pattern.compile("^%AM([a-zA-Z_$][a-zA-Z_.$0-9]*)\\*(.*)\\*%") }

        @JvmStatic
        val PRIMITIVE_DEF_PATTERN: Pattern by lazy { Pattern.compile("") }

        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler
        ): GerberCommand {
            val startLineNumber = lineNumberHandler.lineNumber
            val startLine = stringList[lineNumberHandler.lineNumber]
            val oneLineAMStringBuilder = StringBuilder(startLine)

            try {
                if (oneLineAMStringBuilder.isNotOneLineAMCommand()) {
                    var notEndOfAM = true
                    while (notEndOfAM) {
                        lineNumberHandler.increment()
                        oneLineAMStringBuilder.append(stringList[lineNumberHandler.lineNumber])
                        notEndOfAM = !stringList[lineNumberHandler.lineNumber].contains("%")
                    }
                }

                val matcher = AM_PATTERN.matcher(oneLineAMStringBuilder.toString())
                if (matcher.find()) {
                    val templateName = matcher.group(1)
                    val bodyList = matcher.group(2)
                        .split("*")
                        .map { macroBodyItem -> parseMacroBodyItem(macroBodyItem) }

                    val macroBody = MacroBody(bodyList)
                    return AMCommand(
                        lineNumber = startLineNumber,
                        macroTemplate = MacroTemplate(name = templateName, macroBody = macroBody)
                    )
                } else {
                    throw WrongCommandFormatException(line = startLineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }

        private fun parseMacroBodyItem(item: String): MacroBodyItem =
            if (item.startsWith("$")) {
                MacroVariableDefinition.parseVarDefinition(item)
            } else {
                MacroPrimitiveDefinition.parsePrimitiveDefinition(item)
            }
    }
}
