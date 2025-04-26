package com.example.appmovie

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmsItemDecoration(private val film: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = film
        outRect.right = film
        outRect.bottom = film

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = film
        } else {
            outRect.top = 24
        }
    }
}
//мяуггd
