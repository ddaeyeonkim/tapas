package com.improve777.tapas.ui.utils

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

inline fun AppBarLayout.addOnRangePercentChangedListener(crossinline function: (rangePercent: Float) -> Unit) {
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
        val scrolledRange = abs(verticalOffset + totalScrollRange)
        val rangePercent = (scrolledRange / totalScrollRange.toFloat())
        function(rangePercent)
    })
}