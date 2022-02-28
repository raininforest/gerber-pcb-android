package com.github.raininforest.gerberfilereader

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import com.github.raininforest.logger.Logger
import java.io.File

class FileUriResolverImpl(private val context: Context) : FileUriResolver {
    override fun resolve(uri: Uri): File {
        val fileName = DocumentFile.fromSingleUri(context, uri)?.name ?: "<empty>"
        try {
            context.contentResolver.openInputStream(uri).use { inputStream ->
                context.openFileOutput(fileName, Context.MODE_PRIVATE)
                    .use { outputStream ->
                        inputStream?.copyTo(outputStream)
                    }
            }
        } catch (t: Throwable) {
            Logger.e(t.localizedMessage ?: "Error when copy file $fileName to app folder")
        }
        return context.getFileStreamPath(fileName)
    }
}
