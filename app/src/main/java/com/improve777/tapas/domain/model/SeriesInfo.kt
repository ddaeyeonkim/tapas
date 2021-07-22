package com.improve777.tapas.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesInfo(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val creator: String,
    val isBookCover: Boolean,
    val rgbHex: String,
    val titleColor: Int? = null,
    val bgColor: Int? = null,
) : Parcelable