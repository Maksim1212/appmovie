package com.example.appmovie.movie.presentation.home.rankedadapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RankedHorizontalSpacingItemDecoration(private val popularFilms: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = popularFilms
        outRect.right = OUT_REST_RIGHT

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = OUT_REST_LEFT
        } else {
            outRect.right = OUT_REST_RIGHT
        }
    }

    companion object {
        private const val OUT_REST_RIGHT = 12
        private const val OUT_REST_LEFT = 0
    }
}
