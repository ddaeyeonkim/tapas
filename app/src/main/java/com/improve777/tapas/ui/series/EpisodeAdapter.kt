package com.improve777.tapas.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.improve777.tapas.R
import com.improve777.tapas.base.BaseAdapter
import com.improve777.tapas.base.BaseViewHolder
import com.improve777.tapas.databinding.ItemEpisodeBinding
import com.improve777.tapas.databinding.ItemSectionNameBinding
import com.improve777.tapas.ui.utils.loadUrl

class EpisodeAdapter : BaseAdapter<EpisodeVo, BaseViewHolder<EpisodeVo>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<EpisodeVo> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_section_name -> SectionNameViewHolder(ItemSectionNameBinding.inflate(inflater, parent, false)) as BaseViewHolder<EpisodeVo>
            else -> EpisodeViewHolder(ItemEpisodeBinding.inflate(inflater, parent, false)) as BaseViewHolder<EpisodeVo>
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is EpisodeVo.Item -> R.layout.item_episode
            is EpisodeVo.SectionName -> R.layout.item_section_name
        }
    }
}

class EpisodeViewHolder(private val binding: ItemEpisodeBinding) : BaseViewHolder<EpisodeVo.Item>(binding.root) {
    override fun bind(item: EpisodeVo.Item) {
        val episode = item.episode
        binding.tvScene.text = binding.tvScene.context.getString(R.string.scene_number_format, episode.scene)
        binding.tvTitle.text = episode.title
        binding.tvCreatedDate.text = episode.createdDate
        binding.ivThumbnail.loadUrl(episode.thumbnailUrl)
        binding.ivRead.isVisible = episode.hadRead
    }
}

class SectionNameViewHolder(private val binding: ItemSectionNameBinding) : BaseViewHolder<EpisodeVo.SectionName>(binding.root) {
    override fun bind(item: EpisodeVo.SectionName) {
        binding.tvSectionName.text = item.name
    }
}