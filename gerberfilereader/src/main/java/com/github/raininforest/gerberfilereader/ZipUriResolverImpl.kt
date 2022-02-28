package com.github.raininforest.gerberfilereader

import android.content.Context
import android.net.Uri
import com.github.raininforest.logger.Logger
import java.io.File
import java.util.zip.ZipInputStream

class ZipUriResolverImpl(private val context: Context) : ZipUriResolver {

    override fun resolve(uri: Uri): List<File> {
        val result = mutableListOf<File>()
        try {
            ZipInputStream(context.contentResolver.openInputStream(uri)).use { zipInputStream ->
                var zipEntry = zipInputStream.nextEntry

                while (zipEntry != null) {
                    Logger.d("Zip entry: ${zipEntry.name}")
                    if (!zipEntry.isDirectory) {
                        try {
                            context.openFileOutput(zipEntry.name, Context.MODE_PRIVATE).use { fileOutputStream ->
                                zipInputStream.copyTo(fileOutputStream)
                                val file = context.getFileStreamPath(zipEntry.name)
                                if (file.exists()) {
                                    result.add(file)
                                }
                            }
                        } catch (t: Throwable) {
                            Logger.e(t.localizedMessage ?: "Error while open zip entry: ${zipEntry.name}")
                        }

                    } else {
                        Logger.d("Zip entry is dir. Skip it.")
                    }
                    zipEntry = zipInputStream.nextEntry
                }
            }
        } catch (e: Throwable) {
            Logger.e("Zip extracting error")
        }

        return result
    }
}