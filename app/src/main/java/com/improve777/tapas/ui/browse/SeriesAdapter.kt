package com.improve777.tapas.ui.browse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.improve777.tapas.R
import com.improve777.tapas.base.BaseAdapter
import com.improve777.tapas.base.BaseViewHolder
import com.improve777.tapas.databinding.ItemBrowseBinding
import com.improve777.tapas.databinding.ItemProgressBinding
import com.improve777.tapas.ui.models.SeriesVo
import com.improve777.tapas.ui.utils.loadUrl
import com.improve777.tapas.ui.utils.updateDimensionRatioByThumbnailType

class SeriesAdapter(
    private val onItemClick: (seriesId: Int) -> Unit,
) : BaseAdapter<SeriesVo, BaseViewHolder<SeriesVo>>(DIFF_CALLBACK) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SeriesVo> {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_browse -> {
                val binding =
                    ItemBrowseBinding.inflate(inflater, parent, false)
                val viewHolder = SeriesViewHolder(binding)

                binding.root.setOnClickListener {
                    val seriesVo = getItem(viewHolder.adapterPosition)
                    onItemClick((seriesVo as SeriesVo.Series).id)
                }
                viewHolder as BaseViewHolder<SeriesVo>
            }
            else -> {
                val viewHolder = ProgressViewHolder(
                    ItemProgressBinding.inflate(inflater, parent, false)
                )
                viewHolder as BaseViewHolder<SeriesVo>
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SeriesVo.Series -> R.layout.item_browse
            else -> R.layout.item_progress
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SeriesVo>() {
            override fun areItemsTheSame(oldItem: SeriesVo, newItem: SeriesVo): Boolean {
                return when {
                    oldItem is SeriesVo.Series && newItem is SeriesVo.Series ->
                        oldItem.id == newItem.id && oldItem.index == newItem.index
                    oldItem is SeriesVo.Progress && newItem is SeriesVo.Progress -> true
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: SeriesVo, newItem: SeriesVo): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class SeriesViewHolder(private val binding: ItemBrowseBinding) :
    BaseViewHolder<SeriesVo.Series>(binding.root) {

    override fun bind(item: SeriesVo.Series) {
        updateDimensionRatioByThumbnailType(binding.ivThumbnail, item.isBookCover)
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

class ProgressViewHolder(
    binding: ItemProgressBinding,
) : BaseViewHolder<SeriesVo.Progress>(binding.root) {
    override fun bind(item: SeriesVo.Progress) {
        val params = (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)
        params?.isFullSpan = true
    }
}