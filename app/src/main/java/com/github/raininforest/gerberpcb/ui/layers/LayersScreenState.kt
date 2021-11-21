package com.github.raininforest.gerberpcb.ui.layers

/**
 * Created by Sergey Velesko on 18.07.2021
 */
sealed class LayersScreenState {
    data class Success(val gerberList: List<GerberItemUi>): LayersScreenState()
    data class Error(val error: String): LayersScreenState()
}
