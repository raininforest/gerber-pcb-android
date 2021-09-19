package com.github.raininforest.filereader

/**
 * Created by Sergey Velesko on 19.09.2021
 */
interface FileReader {
    fun read(path: String): List<String>
}