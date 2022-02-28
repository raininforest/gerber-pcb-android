package com.github.raininforest.gerberpcb.model

import android.net.Uri
import com.github.raininforest.gerberfilereader.FileAssetResolver
import com.github.raininforest.gerberfilereader.FileUriResolver
import com.github.raininforest.gerberfilereader.ZipUriResolver
import com.github.raininforest.gerberpcb.model.entity.Gerber
import com.github.raininforest.gerberpcb.model.entity.GerberResult
import com.github.raininforest.gerberpcb.model.entity.LoadingResult
import com.github.raininforest.logger.Logger

/**
 * [GerberRepository] implementation
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberRepositoryImpl(
    private val gerberProcessor: GerberProcessor,
    private val zipUriResolver: ZipUriResolver,
    private val fileUriResolver: FileUriResolver,
    private val fileAssetResolver: FileAssetResolver
) : GerberRepository {

    private val _gerbersDictionary: MutableMap<String, Gerber> = mutableMapOf()
    private val _gerbers = mutableListOf<Gerber>()

    override val gerbers: List<Gerber>
        get() = _gerbers

    override suspend fun addItem(fileUri: Uri, fileName: String): LoadingResult {
        return if (fileName.endsWith(".zip")) {
            val filesFromZip = zipUriResolver resolve fileUri
            val results = filesFromZip.map { gerberFile ->
                gerberProcessor.process(gerberFile)
            }
            handleSeveralResults(results)
        } else {
            val file = fileUriResolver resolve fileUri
            val result = gerberProcessor.process(file)
            handleResult(result)
        }
    }

    override suspend fun loadSamples(): LoadingResult {
        val sampleFiles = SAMPLES.map { sampleFileName ->
            fileAssetResolver resolve sampleFileName
        }
        val results = sampleFiles.map { sampleFile ->
            gerberProcessor.process(sampleFile)
        }
        return handleSeveralResults(results)
    }

    private fun handleResult(result: GerberResult): LoadingResult {
        return when (result) {
            is GerberResult.Error -> {
                Logger.e(result.errorMessage)
                LoadingResult.Error(result.errorMessage)
            }
            is GerberResult.Success -> {
                _gerbersDictionary[result.gerber.id] = result.gerber
                updateList()
                LoadingResult.Success
            }
        }
    }

    private fun handleSeveralResults(results: List<GerberResult>): LoadingResult {
        return if (results.isNotEmpty()) {
            results.forEach { gerberResult ->
                when (gerberResult) {
                    is GerberResult.Error -> {
                        Logger.e(gerberResult.errorMessage)
                    }
                    is GerberResult.Success -> {
                        _gerbersDictionary[gerberResult.gerber.id] = gerberResult.gerber
                        updateList()
                    }
                }
            }
            LoadingResult.Success
        } else {
            LoadingResult.Error("No file processed")
        }
    }

    override fun removeItem(id: String) {
        _gerbersDictionary.remove(id)
        updateList()
    }

    override fun changeItemVisibility(id: String, visibility: Boolean) {
        _gerbersDictionary[id]?.isVisible = visibility
        updateList()
    }

    private fun updateList() {
        _gerbers.clear()
        _gerbers.addAll(_gerbersDictionary.values.toList())
    }

    companion object {
        private val SAMPLES = listOf(
            "module.board",
            "module.top",
            "module.topmask",
            "module.topsilk"
        )
    }
}
