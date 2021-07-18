package com.github.raininforest.gerberpcb.viewmodel.layers

import com.github.raininforest.gerberpcb.model.GerberList

sealed class LayersScreenState {
    data class Success(val gerberList: GerberList): LayersScreenState()
    data class Error(val error: Throwable): LayersScreenState()
    object Loading : LayersScreenState()
}
