package com.improve777.tapas.ui.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.improve777.tapas.base.BaseViewModel
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.model.Pagination
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.domain.model.State
import com.improve777.tapas.domain.repository.BrowseRepository
import com.improve777.tapas.ui.mapper.toVo
import com.improve777.tapas.ui.models.SeriesVo
import com.improve777.tapas.ui.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val browseRepository: BrowseRepository,
) : BaseViewModel() {

    private val _seriesList = MutableLiveData<List<SeriesVo>>()
    val seriesList: LiveData<List<SeriesVo>> = _seriesList

    private val _goToSeriesEvent = MutableLiveData<Event<SeriesInfo>>()
    val goToSeriesEvent: LiveData<Event<SeriesInfo>> = _goToSeriesEvent

    private val _openRetrySeriesInfoEvent = MutableLiveData<Event<SeriesInfoError>>()
    val openRetrySeriesInfoEvent: LiveData<Event<SeriesInfoError>> = _openRetrySeriesInfoEvent

    private val _hasNextPage = MutableLiveData<Boolean>()
    val hasNextPage: LiveData<Boolean> = _hasNextPage

    private var pagination = Pagination(1, true)

    fun loadBrowse(loadMore: Boolean = false) {
        if (loadMore && !pagination.hasNext) {
            return
        }

        if (!loadMore) {
            pagination = Pagination(1, true)
            _seriesList.value = emptyList()
        }

        viewModelScope.launch {
            browseRepository.getBrowse(pagination.page)
                .collect(this@BrowseViewModel::collectBrowseList)

            _loading.value = false
        }
    }

    private fun collectBrowseList(state: State<Browse>) {
        when (state) {
            is State.Success -> {
                pagination = state.data.pagination
                _hasNextPage.value = pagination.hasNext

                val seriesVoList: MutableList<SeriesVo> = state.data.seriesList
                    .mapIndexed { index, series -> series.toVo(index) }
                    .toMutableList()

                if (pagination.hasNext) {
                    seriesVoList.add(SeriesVo.Progress)
                }

                val beforeList: List<SeriesVo> =
                    (_seriesList.value ?: emptyList()).filterIsInstance<SeriesVo.Series>()
                val currentList: List<SeriesVo> = beforeList + seriesVoList
                _seriesList.value = currentList

                if (_seriesList.value.isNullOrEmpty()) {
                    _error.value = State.Error.EMPTY
                }
            }
            is State.Error -> {
                _error.value = state
            }
            State.Loading -> {
                if (pagination.page == 1) {
                    _loading.value = true
                }
            }
        }
    }

    fun goToSeriesView(seriesId: Int) {
        loadSeriesInfo(seriesId)
    }

    private fun loadSeriesInfo(seriesId: Int) {
        viewModelScope.launch {
            browseRepository.getSeriesInfo(seriesId)
                .collect {
                    collectSeriesInfo(seriesId, it)
                }
            _loading.value = false
        }
    }

    private fun collectSeriesInfo(seriesId: Int, state: State<SeriesInfo>) {
        when (state) {
            is State.Success -> {
                _goToSeriesEvent.value = Event(state.data)
            }
            is State.Error -> {
                _openRetrySeriesInfoEvent.value = Event(SeriesInfoError(seriesId))
            }
            State.Loading -> {
                _loading.value = true
            }
        }
    }
}