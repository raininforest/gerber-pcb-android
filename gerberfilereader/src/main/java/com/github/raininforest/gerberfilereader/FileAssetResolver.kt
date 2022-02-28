package com.github.raininforest.gerberfilereader

import java.io.File

interface FileAssetResolver {
    infix fun resolve(fileName: String): File
}