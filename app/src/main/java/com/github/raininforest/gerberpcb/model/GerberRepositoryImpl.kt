package com.github.raininforest.gerberpcb.model

import android.net.Uri
import com.github.raininforest.gerberpcb.model.entity.Gerber
import com.github.raininforest.gerberpcb.model.entity.GerberResult
import com.github.raininforest.logger.Logger

/**
 * [GerberRepository] implementation
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberRepositoryImpl(
    private val gerberProcessor: GerberProcessor
) : GerberRepository {

    private val _gerbersDictionary: MutableMap<String, Gerber> = mutableMapOf()
    private val _gerbers = mutableListOf<Gerber>()

    override val gerbers: List<Gerber>
        get() = _gerbers

    override suspend fun addItem(fileUri: Uri, fileName: String) =
        when (val result = gerberProcessor.process(fileUri, fileName)) {
            is GerberResult.Error -> {
                Logger.e(result.errorMessage)
                result
            }
            is GerberResult.Success -> {
                _gerbersDictionary[result.gerber.id] = result.gerber
                updateList()
                result
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
}
