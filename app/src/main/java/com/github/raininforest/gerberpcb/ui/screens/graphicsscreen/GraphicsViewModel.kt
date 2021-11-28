package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.raininforest.gerberpcb.model.GerberRepository
import com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview.GerberLayerUi

/**
 * [ViewModel] for [GraphicsFragment]
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GraphicsViewModel(
    private val gerberRepository: GerberRepository
) : ViewModel() {

    private val _data = MutableLiveData<List<GerberLayerUi>>()

    val data: LiveData<List<GerberLayerUi>>
        get() = _data.apply {
            value = gerberRepository
                .gerbers
                .filter { it.isVisible }
                .map { GerberLayerUi(it.data, it.color) }
        }
}