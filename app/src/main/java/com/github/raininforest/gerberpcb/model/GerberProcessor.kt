package com.github.raininforest.gerberpcb.model

import com.github.raininforest.GraphicsProcessor
import com.github.raininforest.gerberfilereader.GerberFileReader
import com.github.raininforest.gerberpcb.model.entity.Gerber
import com.github.raininforest.gerberpcb.model.entity.GerberResult
import com.github.raininforest.gerberpcb.ui.utils.generateLayerColor
import com.github.raininforest.syntaxparser.api.SyntaxParser
import java.io.File

class GerberProcessor(
    private val fileReader: GerberFileReader,
    private val parser: SyntaxParser,
    private val graphicsProcessor: GraphicsProcessor
) {
    fun process(file: File): GerberResult {
        return try {
            val graphicStream =
                graphicsProcessor.process(
                    parser.parse(
                        stringList = fileReader.read(file),
                        name = file.name
                    )
                )
            GerberResult.Success(Gerber(name = file.name, data = graphicStream, color = generateLayerColor()))
        } catch (e: Throwable) {
            GerberResult.Error(errorMessage = e.localizedMessage ?: "Reading file error: ${file.name}")
        }
    }
}