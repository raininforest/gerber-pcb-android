package com.github.raininforest.gerberpcb.view.layers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class SpaceDecorator(private val space: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if (position >= 0) {
            outRect.set(space, space, space, 0)
        }
    }
}