package com.github.raininforest.gerberpcb.model

import android.net.Uri
import androidx.annotation.ColorInt
import com.github.raininforest.gerberpcb.model.entity.Gerbers
import com.github.raininforest.gerberpcb.model.entity.LoadingResult
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for [GerberRepository]
 *
 * Created by Sergey Velesko on 18.07.2021
 */
interface GerberRepository {
    val gerbers: StateFlow<Gerbers>
    suspend fun addItem(fileUri: Uri, fileName: String): LoadingResult
    suspend fun loadSamples(): LoadingResult
    fun removeItem(id: String)
    fun changeItemVisibility(id: String, visibility: Boolean)

    @ColorInt
    fun getColor(id: String): Int
    fun setColor(id: String, @ColorInt color: Int)
    fun getOpacity(id: String): Float
    fun setOpacity(id: String, opacity: Float)
}
