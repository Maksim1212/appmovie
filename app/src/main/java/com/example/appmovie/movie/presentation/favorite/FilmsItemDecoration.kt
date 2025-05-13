package com.example.appmovie.movie.presentation.favorite

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmsItemDecoration(private val film: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = film
        outRect.right = film
        outRect.bottom = OUT_REST_BOTTOM

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = film
        } else {
            outRect.top = OUT_REST_TOP
        }
    }

    companion object {
        private const val OUT_REST_TOP = 30
        private const val OUT_REST_BOTTOM = 10
    }
}
