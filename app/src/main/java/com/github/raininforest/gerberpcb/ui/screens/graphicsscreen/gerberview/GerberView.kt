package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GerberView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle), KoinComponent {

    private val renderer: GerberRenderer by inject()

    private val _data: MutableList<GerberLayerUi> = mutableListOf()
    var data: List<GerberLayerUi>
        set(value) {
            _data.clear()
            _data.addAll(value)
        }
        get() = _data

    override fun onDraw(canvas: Canvas?) {
        _data.forEach { gerberLayer ->
            canvas?.let { canvas ->
                renderer.render(data = gerberLayer.data, canvas = canvas, color = gerberLayer.color)
            }
        }
    }
}
