package com.github.raininforest.gerberfilereader

import java.io.File

/**
 * Reads gerber file and return list of strings
 *
 * Created by Sergey Velesko on 19.09.2021
 */
interface GerberFileReader {
    fun read(file: File): List<String>
}