package com.github.raininforest.gerberpcb.model.entity

import android.graphics.Color
import androidx.annotation.ColorInt
import com.github.raininforest.GerberImageResult
import java.util.*

/**
 * Created by Sergey Velesko on 31.10.2021
 */
data class Gerber(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    @ColorInt
    var color: Int = DEFAULT_COLOR,
    var opacity: Float = DEFAULT_OPACITY,
    var isVisible: Boolean = false,
    val data: GerberImageResult
) {
    companion object {
        const val DEFAULT_OPACITY = 1.0f
        const val DEFAULT_COLOR = Color.WHITE
    }
}
