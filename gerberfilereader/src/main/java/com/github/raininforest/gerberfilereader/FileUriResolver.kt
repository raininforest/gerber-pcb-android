package com.github.raininforest.gerberfilereader

import android.net.Uri
import java.io.File

interface FileUriResolver {
    infix fun resolve(uri: Uri): File
}