package com.github.raininforest.syntaxparser.impl.utils

import java.io.*

/**
 * Utils for tests
 *
 * Created by Sergey Velesko on 02.11.2021
 */
fun readTestFile(file: File): List<String> {
    val result = mutableListOf<String>()
    try {
        BufferedReader(FileReader(file)).use { reader ->
            var eof = false
            while (!eof) {
                val line: String? = reader.readLine()
                if (line == null) {
                    eof = true
                } else {
                    result.add(line)
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        throw FileNotFoundException()
    }
    return result
}
