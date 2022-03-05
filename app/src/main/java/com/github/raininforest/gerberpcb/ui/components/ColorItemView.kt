package com.github.raininforest.gerberpcb.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Checkable
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.ColorItemLayoutBinding

class ColorItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Checkable {

    private val binding = ColorItemLayoutBinding.inflate(LayoutInflater.from(context), this)

    @DrawableRes
    private val _uncheckedBackground: Int

    @DrawableRes
    private val _checkedBackground: Int

    @ColorInt
    private val _color: Int
    val color: Int
        get() = _color

    private var _checked: Boolean = false
        set(value) {
            setState(value)
            field = value
        }

    var clickListener: (view: ColorItemView) -> Unit = {}

    init {
        val arr = context.obtainStyledAttributes(
            attrs,
            R.styleable.ColorItemView,
            defStyleAttr,
            0
        )
        _uncheckedBackground =
            arr.getResourceId(
                R.styleable.ColorItemView_unchecked_background,
                R.drawable.color_item_background_unchecked
            )
        _checkedBackground =
            arr.getResourceId(
                R.styleable.ColorItemView_checked_background,
                R.drawable.color_item_background_checked
            )
        _color = arr.getColor(R.styleable.ColorItemView_color, Color.BLACK)
        arr.recycle()

        binding.textView.isClickable = true
        binding.textView.isFocusable = true

        _checked = false

        binding.textView.background.setTint(_color)
        binding.textView.setOnClickListener {
            clickListener.invoke(this)
        }
    }

    override fun setChecked(checked: Boolean) {
        _checked = checked
    }

    override fun isChecked(): Boolean = _checked

    override fun toggle() {
        _checked = !_checked
    }

    private fun setState(checked: Boolean) {
        if (checked) {
            binding.checkedLayer.visibility = View.VISIBLE
        } else {
            binding.checkedLayer.visibility = View.GONE
        }
    }
}