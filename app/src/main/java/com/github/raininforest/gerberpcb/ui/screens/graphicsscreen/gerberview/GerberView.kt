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

    private var isInitialized: Boolean = false

    private val _data: MutableList<GerberLayerUi> = mutableListOf()
    var data: List<GerberLayerUi>
        set(value) {
            _data.clear()
            _data.addAll(value)
        }
        get() = _data

    override fun onDraw(canvas: Canvas?) {
        setupCanvas(canvas)
        _data.forEach { gerberLayer ->
            canvas?.let { canvas ->
                renderer.render(data = gerberLayer.data, canvas = canvas, color = gerberLayer.color)
            }
        }
    }

    private fun setupCanvas(canvas: Canvas?) {
        if (!isInitialized) {
            canvas?.scale(1f, -1f, width / 2f, height / 2f)
            canvas?.scale(DEFAULT_SCALE, DEFAULT_SCALE)
            isInitialized = true
        }
    }

    companion object {
        private const val DEFAULT_SCALE = 50f
    }
}
