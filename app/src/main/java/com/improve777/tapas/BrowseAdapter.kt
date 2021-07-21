package com.improve777.tapas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.improve777.tapas.base.BaseAdapter
import com.improve777.tapas.base.BaseViewHolder
import com.improve777.tapas.databinding.ItemBrowseBinding
import com.improve777.tapas.domain.model.Browse

class BrowseAdapter : BaseAdapter<Browse, ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBrowseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}

class ViewHolder(private val binding: ItemBrowseBinding) : BaseViewHolder<Browse>(binding.root) {

    override fun bind(item: Browse) {
        val layoutParams = binding.ivThumbnail.layoutParams as? ConstraintLayout.LayoutParams
        layoutParams?.dimensionRatio = if (item.isBookCover) "1:1.5" else "1:1"
        binding.ivThumbnail.layoutParams = layoutParams
        // TODO: 2021/07/21 이미지 로드 추가

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