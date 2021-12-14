package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import com.github.raininforest.syntaxparser.impl.LineNumberHandler
import com.github.raininforest.syntaxparser.impl.MultiStringParsable
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import java.util.regex.Pattern

/**
 * Creates an aperture, attaches the aperture attributes at that moment
 * in the attribute dictionary to it and adds it to the [ApertureDictionary].
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class ADCommand(
    val apertureId: String,
    val apertureTemplateName: String,
    val parameters: List<Double>,
    override val lineNumber: Int,
) : GerberCommand {

    override fun perform(processor: CommandProcessor) {
        val template = processor.templateDictionary.get(id = apertureTemplateName)
        val aperture = template.buildAperture(apertureId, parameters)
        processor.apertureDictionary.add(aperture)
    }

    internal companion object : MultiStringParsable {

        private val AD_PATTERN: Pattern by lazy { Pattern.compile("^%ADD([1-9]\\d+)([\\w\\.]+),?(.*)?\\*%") }

        override fun parse(
            stringList: List<String>,
            lineNumberHandler: LineNumberHandler
        ): GerberCommand {
            val matcher = AD_PATTERN.matcher(stringList[lineNumberHandler.lineNumber])
            try {
                if (matcher.find()) {
                    val apertureId = matcher.group(1)
                    val apertureTemplateName = matcher.group(2)
                    val parameters: List<Double> = matcher.group(3)
                        .split("X")
                        .filter { it.isNotEmpty() }
                        .map { it.toDouble() }

                    return ADCommand(
                        lineNumber = lineNumberHandler.lineNumber,
                        apertureId = apertureId,
                        apertureTemplateName = apertureTemplateName,
                        parameters = parameters
                    )
                } else {
                    throw WrongCommandFormatException(line = lineNumberHandler.lineNumber)
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }

}
