package com.github.raininforest.gerberpcb.ui.screens.layersscreen

import com.github.raininforest.gerberpcb.model.entity.Gerber

fun List<Gerber>.toScreenState(): LayersScreenState =
    LayersScreenState.Success(
        this.map {
            GerberItemUi(
                id = it.id,
                name = it.name,
                checked = it.isVisible
            )
        }
    )
