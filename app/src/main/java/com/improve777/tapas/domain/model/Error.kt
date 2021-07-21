package com.improve777.tapas.domain.model

import com.improve777.tapas.R

data class Error(
    val iconRes: Int,
    val message: Int,
) {
    companion object {
        val Empty = Error(R.drawable.ic_empty_box, R.string.the_data_is_not_available)
    }
}