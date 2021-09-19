package com.github.raininforest.gerberpcb.ui.layers

import androidx.lifecycle.LiveData
import com.github.raininforest.gerberpcb.model.GerberItemUi

/**
 * Created by Sergey Velesko on 18.07.2021
 */
sealed class LayersScreenState {
    data class Success(val gerberList: List<GerberItemUi>): LayersScreenState()
    data class Error(val error: String): LayersScreenState()
    data class Loading(val progress: LiveData<String>) : LayersScreenState()
}
