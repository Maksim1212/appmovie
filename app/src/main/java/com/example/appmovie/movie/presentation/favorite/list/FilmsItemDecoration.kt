package com.example.appmovie.movie.presentation.favorite.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmsItemDecoration(
    private val bottomOffset: Int,
    private val leftOffset: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = bottomOffset
        outRect.left = leftOffset
    }
}
