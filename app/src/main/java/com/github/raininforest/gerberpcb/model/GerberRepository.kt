package com.github.raininforest.gerberpcb.model

import android.net.Uri
import com.github.raininforest.gerberpcb.model.entity.Gerber
import com.github.raininforest.gerberpcb.model.entity.LoadingResult

/**
 * Interface for [GerberRepository]
 *
 * Created by Sergey Velesko on 18.07.2021
 */
interface GerberRepository {
    val gerbers: List<Gerber>
    suspend fun addItem(fileUri: Uri, fileName: String): LoadingResult
    suspend fun loadSamples(): LoadingResult
    fun removeItem(id: String)
    fun changeItemVisibility(id: String, visibility: Boolean)
}