package com.github.raininforest.gerberfilereader

import android.content.Context
import com.github.raininforest.logger.Logger
import java.io.File

class FileAssetResolverImpl(private val context: Context) : FileAssetResolver {

    override fun resolve(fileName: String): File {
        try {
            context.assets.open(fileName).use { inputStream ->
                context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (t: Throwable) {
            Logger.e(t.localizedMessage ?: "Error when copy file $fileName to app folder")
        }
        return context.getFileStreamPath(fileName)
    }
}