package com.github.raininforest.gerberfilereader

import android.net.Uri
import java.io.File

interface ZipUriResolver {
    infix fun resolve(uri: Uri): List<File>
}