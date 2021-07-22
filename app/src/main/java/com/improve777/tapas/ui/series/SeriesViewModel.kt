package com.improve777.tapas.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.improve777.tapas.domain.model.State
import com.improve777.tapas.base.BaseViewModel
import com.improve777.tapas.domain.model.Episode
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.domain.repository.BrowseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val browseRepository: BrowseRepository,
) : BaseViewModel() {

    private val _seriesInfo = MutableLiveData<SeriesInfo>()
    val seriesInfo: LiveData<SeriesInfo> = _seriesInfo

    private val _episodeVoList = MutableLiveData<List<EpisodeVo>>()
    val episodeVoList: LiveData<List<EpisodeVo>> = _episodeVoList

    fun setData(seriesInfo: SeriesInfo) {
        _seriesInfo.value = seriesInfo
    }

    fun loadEpisodeList() {
        val seriesId = seriesInfo.value?.id ?: return

        viewModelScope.launch {
            browseRepository.getEpisodeList(seriesId)
                .collect(this@SeriesViewModel::collectEpisodeList)

            _loading.value = false
        }
    }

    private suspend fun collectEpisodeList(state: State<List<Episode>>) {
        when (state) {
            is State.Success -> {
                _episodeVoList.value = withContext(Dispatchers.Default) {
                    state.data.map {
                        EpisodeVo.Item(it)
                    }    
                }
            }
            is State.Error -> {
                _error.value = state
            }
            State.Loading -> {
                _loading.value = true
            }
        }
    }
}