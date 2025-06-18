package com.example.appmovie.movie.presentation.home.componentsforcategories

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CategoriesFilmsItemDecoration(
    private val topOffset: Int,
    private val rightOffset: Int,
    private val bottomOffset: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = topOffset
        outRect.right = rightOffset
        outRect.bottom = bottomOffset
    }
}
