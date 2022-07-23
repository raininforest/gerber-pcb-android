package com.github.raininforest.gerberpcb.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.raininforest.gerberpcb.databinding.ColorSelectPanelBinding

class ColorSelectPanel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val b = ColorSelectPanelBinding.inflate(LayoutInflater.from(context), this)

    private var currentSelectedItemView: ColorItemView? = null

    private val itemList = listOf(b.color1, b.color2, b.color3, b.color4, b.color5, b.color6, b.color7, b.color8)
        .onEach { colorItemView ->
            colorItemView.clickListener = { itemView ->
                if (currentSelectedItemView != itemView) {
                    currentSelectedItemView?.toggle()
                    currentSelectedItemView = itemView
                    itemView.toggle()
                    onColorSelected.invoke(itemView.color)
                }
            }
        }

    var onColorSelected: (colorInt: Int) -> Unit = {}

    fun setInitialColor(colorInt: Int) {
        itemList.find { it.color == colorInt }?.let {
            it.isChecked = true
            currentSelectedItemView?.toggle()
            currentSelectedItemView = it
        }
    }
}
