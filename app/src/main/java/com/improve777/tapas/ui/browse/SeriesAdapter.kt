package com.improve777.tapas.ui.browse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.improve777.tapas.base.BaseAdapter
import com.improve777.tapas.base.BaseViewHolder
import com.improve777.tapas.databinding.ItemBrowseBinding
import com.improve777.tapas.domain.model.Series
import com.improve777.tapas.ui.utils.loadUrl

class SeriesAdapter(
    private val onItemClick: (seriesId: Int) -> Unit,
) : BaseAdapter<Series, ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBrowseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            onItemClick(items[viewHolder.adapterPosition].id)
        }
        return viewHolder
    }
}

class ViewHolder(private val binding: ItemBrowseBinding) : BaseViewHolder<Series>(binding.root) {

    override fun bind(item: Series) {
        val layoutParams = binding.ivThumbnail.layoutParams as? ConstraintLayout.LayoutParams
        layoutParams?.dimensionRatio = if (item.isBookCover) "1:1.5" else "1:1"
        binding.ivThumbnail.layoutParams = layoutParams
        binding.ivThumbnail.loadUrl(item.thumbnailUrl)

        binding.tvTitle.isVisible = !item.isBookCover && item.title.isNotBlank()
        binding.tvTitle.text = item.title

        binding.tvGenre.text = item.genre
        binding.tvLikeCount.text = convertUnit(item.likeCount)
    }

    private fun convertUnit(count: Int): String {
        val kilo = count / 1000
        if (kilo > 0) {
            return String.format("%,dK", kilo)
        }
        return String.format("%,d", count)
    }
}