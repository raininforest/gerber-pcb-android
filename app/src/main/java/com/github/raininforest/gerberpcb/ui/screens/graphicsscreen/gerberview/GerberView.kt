package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.github.raininforest.GraphicsObject

@SuppressLint("ViewConstructor")
class GerberView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,

    defStyle: Int = 0,
    private val renderer: GerberRenderer
) : View(context, attrs, defStyle) {

    private val _data: MutableList<GraphicsObject> = mutableListOf()
    var data: List<GraphicsObject>
        set(value) {
            _data.clear()
            _data.addAll(value)
        }
        get() = _data

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            renderer.render(data = _data, canvas = it)
        }
    }
}
