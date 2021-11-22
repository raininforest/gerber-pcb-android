package com.github.raininforest.gerberpcb.model

import android.net.Uri
import com.github.raininforest.GraphicsProcessor
import com.github.raininforest.gerberfilereader.GerberFileReader
import com.github.raininforest.gerberpcb.model.entity.Gerber
import com.github.raininforest.gerberpcb.model.entity.GerberResult
import com.github.raininforest.syntaxparser.api.SyntaxParser

class GerberProcessor(
    private val fileReader: GerberFileReader,
    private val parser: SyntaxParser,
    private val graphicsProcessor: GraphicsProcessor
) {
    fun process(uri: Uri, fileName: String): GerberResult {
        return try {
            val graphicStream = graphicsProcessor.process(parser.parse(fileReader.read(uri)))
            GerberResult.Success(Gerber(name = fileName, data = graphicStream))
        } catch (e: Throwable) {
            GerberResult.Error(errorMessage = e.localizedMessage ?: "Reading file error: $fileName")
        }
    }
}