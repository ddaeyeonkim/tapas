package com.improve777.tapas.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>,
) : ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}