package com.github.raininforest.gerberfilereader

import com.github.raininforest.logger.Logger
import java.io.File

/**
 * Reads gerber file and return list of strings
 *
 * Created by Sergey Velesko on 19.09.2021
 */
class GerberFileReaderImpl : GerberFileReader {

    override fun read(file: File): List<String> {
        val result = mutableListOf<String>()
        try {
            file.bufferedReader().useLines {
                result.addAll(it.toList())
            }
        } catch (e: Throwable) {
            Logger.e("Error when read file: ${file.name}!")
            Logger.e(e.message ?: "")
        }
        return result
    }
}