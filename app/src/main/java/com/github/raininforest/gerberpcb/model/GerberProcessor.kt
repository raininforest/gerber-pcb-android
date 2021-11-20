package com.github.raininforest.gerberpcb.model

import android.net.Uri
import com.github.raininforest.gerberfilereader.GerberFileReader
import com.github.raininforest.syntaxparser.api.SyntaxParser

class GerberProcessor(
    private val fileReader: GerberFileReader,
    private val parser: SyntaxParser,
    //TODO private val graphicsProcessor: GraphicsProcessor
) {
    fun process(uri: Uri): GerberResult {
        return try {
            //TODO graphicsProcessor.createGraphicsStream(parser.parse(fileReader.read(uri)))
            parser.parse(stringList = fileReader.read(uri), name = uri.lastPathSegment ?: "<empty>")
            GerberResult.Success(Gerber(name = uri.lastPathSegment ?: "<empty>"))
        } catch (e: Throwable) {
            GerberResult.Error(errorMessage = e.localizedMessage ?: "Reading file error!")
        }
    }
}