package com.improve777.tapas.ui.utils

import androidx.core.content.ContextCompat
import com.improve777.tapas.databinding.LayoutErrorBinding
import com.improve777.tapas.ui.models.Error

fun LayoutErrorBinding.fetchError(error: Error) {
    ivError.setImageDrawable(ContextCompat.getDrawable(root.context, error.iconRes))
    tvErrorMessage.text = root.context.getString(error.message)
}