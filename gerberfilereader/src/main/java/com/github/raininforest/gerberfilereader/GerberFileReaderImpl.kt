package com.github.raininforest.gerberfilereader

import android.content.Context
import android.net.Uri
import timber.log.Timber

/**
 * Reads gerber file and return list of strings
 *
 * Created by Sergey Velesko on 19.09.2021
 */
class GerberFileReaderImpl(
    private val context: Context
) : GerberFileReader {

    override fun read(uri: Uri): List<String> {
        val result = mutableListOf<String>()
        try {
            context.contentResolver
                ?.openInputStream(uri)
                ?.bufferedReader()
                .use { bufferedReader ->
                    bufferedReader
                        ?.readLines()
                        ?.let { list -> result.addAll(list) }
                }
        } catch (e: Throwable) {
            Timber.e("File reading error!")
            Timber.e(e)
        }
        return result
    }
}