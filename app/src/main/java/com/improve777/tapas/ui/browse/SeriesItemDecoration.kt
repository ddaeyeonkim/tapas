package com.improve777.tapas.ui.browse

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.improve777.tapas.ui.utils.DP

class SeriesItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val holder = parent.getChildViewHolder(view)

        if (holder !is SeriesViewHolder) return

        val outerPadding = 8.DP
        val innerPadding = 4.DP

        if (position == 0 || position == 1) {
            outRect.top = outerPadding
            outRect.bottom = outerPadding
        } else {
            outRect.bottom = outerPadding
        }

        val params = view.layoutParams as? StaggeredGridLayoutManager.LayoutParams ?: return

        if (params.spanIndex == 0) {
            outRect.left = outerPadding
            outRect.right = innerPadding
        } else {
            outRect.left = innerPadding
            outRect.right = outerPadding
        }
    }
}