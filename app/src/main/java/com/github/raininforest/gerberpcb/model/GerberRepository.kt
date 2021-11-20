package com.github.raininforest.gerberpcb.model

import android.net.Uri

/**
 * Interface for [GerberRepository]
 *
 * Created by Sergey Velesko on 18.07.2021
 */
interface GerberRepository {
    suspend fun list(): List<GerberItemUi>
    suspend fun addItem(filename: Uri): Boolean
    suspend fun removeItem(id: String)
    suspend fun changeItemVisibility(id: String, visibility: Boolean)
}