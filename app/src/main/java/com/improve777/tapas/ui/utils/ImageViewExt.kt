package com.improve777.tapas.ui.utils

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.improve777.tapas.GlideApp

fun ImageView.loadUrl(url: String) {
    if (url.isBlank()) return

    GlideApp.with(this)
        .load(url)
        .into(this)
}

fun updateDimensionRatioByThumbnailType(ivThumbnail: ImageView, isBookCover: Boolean) {
    val layoutParams = ivThumbnail.layoutParams as? ConstraintLayout.LayoutParams
    layoutParams?.dimensionRatio = if (isBookCover) "1:1.5" else "1:1"
    ivThumbnail.layoutParams = layoutParams
}