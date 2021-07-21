package com.improve777.tapas.ui.utils

import android.widget.ImageView
import com.improve777.tapas.GlideApp

fun ImageView.loadUrl(url: String) {
    if (url.isBlank()) return

    GlideApp.with(this)
        .load(url)
        .into(this)
}