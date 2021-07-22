package com.improve777.tapas.ui.series

import android.graphics.Rect
import android.view.View
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import com.improve777.tapas.ui.utils.DP

class EpisodeItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)

        val outerPadding = 16.DP
        val innerPadding = 8.DP

        if (position == 0) {
            view.updatePadding(left = outerPadding,
                top = outerPadding,
                right = outerPadding,
                bottom = innerPadding)
            return
        }

        val isLastItem = position == (parent.adapter?.itemCount ?: 0) - 1
        if (isLastItem) {
            view.updatePadding(left = outerPadding,
                top = innerPadding,
                right = outerPadding,
                bottom = outerPadding)
            return
        }

        view.updatePadding(left = outerPadding,
            top = innerPadding,
            right = outerPadding,
            bottom = innerPadding)
    }
}