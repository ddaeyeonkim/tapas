package com.improve777.tapas.ui.series

import com.improve777.tapas.domain.model.Episode

sealed class EpisodeVo {
    data class Item(val episode: Episode) : EpisodeVo()
    data class SectionName(val name: String) : EpisodeVo()
}