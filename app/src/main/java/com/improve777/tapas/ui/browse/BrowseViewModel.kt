package com.improve777.tapas.ui.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.improve777.tapas.State
import com.improve777.tapas.base.BaseViewModel
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.model.Error
import com.improve777.tapas.domain.model.Pagination
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.domain.repository.BrowseRepository
import com.improve777.tapas.ui.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val browseRepository: BrowseRepository,
) : BaseViewModel() {

    private val _browseList = MutableLiveData<List<Browse>>()
    val browseList: LiveData<List<Browse>> = _browseList

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _goToSeriesEvent = MutableLiveData<Event<SeriesInfo>>()
    val goToSeriesEvent: LiveData<Event<SeriesInfo>> = _goToSeriesEvent

    private var pagination = Pagination(1, true)

    fun getBrowseList(loadMore: Boolean = false) {
        if (!pagination.hasNext) {
            return
        }

        if (!loadMore) {
            _browseList.value = emptyList()
        }

        viewModelScope.launch {
            browseRepository.getBrowseList(pagination.page)
                .collect(this@BrowseViewModel::collectBrowseList)

            _loading.value = false
        }
    }

    private suspend fun collectBrowseList(state: State<List<Browse>>) {
        when (state) {
            is State.Success -> {
                pagination = Pagination(pagination.page + 1, true)

                val currentList = _browseList.value ?: emptyList()
                _browseList.value = currentList + state.data

                if (_browseList.value.isNullOrEmpty()) {
                    _error.value = Error.Empty
                }
            }
            is State.Error -> {
                if (_browseList.value.isNullOrEmpty()) {
                    _error.value = Error.Empty
                }
            }
            State.Loading -> {
                _loading.value = true
            }
        }
    }

    fun goToSeriesView() {
        viewModelScope.launch {
            browseRepository.getSeriesInfo(0)
                .collect(this@BrowseViewModel::collectSeriesInfo)

            _loading.value = false
        }
    }

    private fun collectSeriesInfo(state: State<SeriesInfo>) {
        when (state) {
            is State.Success -> {
                _goToSeriesEvent.value = Event(state.data)
            }
            is State.Error -> {
                // TODO: 2021/07/21 에러처리
            }
            State.Loading -> {
                _loading.value = true
            }
        }
    }
}

