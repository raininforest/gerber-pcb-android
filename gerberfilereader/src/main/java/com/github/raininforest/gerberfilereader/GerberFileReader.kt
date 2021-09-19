package com.github.raininforest.gerberfilereader

import android.net.Uri

/**
 * Reads gerber file and return list of strings
 *
 * Created by Sergey Velesko on 19.09.2021
 */
interface GerberFileReader {
    fun read(uri: Uri): List<String>
}