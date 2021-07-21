package com.improve777.tapas.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    protected val items = mutableListOf<T>()

    fun submitList(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeInserted(this.items.size, items.size)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}