package com.improve777.tapas.ui.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.improve777.tapas.State
import com.improve777.tapas.base.BaseViewModel
import com.improve777.tapas.domain.model.Browse
import com.improve777.tapas.domain.model.Error
import com.improve777.tapas.domain.repository.BrowseRepository
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

    private var page = 1

    fun getBrowseList(page: Int) {
        this.page = page

        viewModelScope.launch {
            browseRepository.getBrowseList(page)
                .collect(this@BrowseViewModel::collectBrowseList)
        }
    }

    private suspend fun collectBrowseList(state: State<List<Browse>>) {
        when (state) {
            is State.Success -> {
                val currentList = if (page == 1) emptyList() else (_browseList.value ?: emptyList())
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
                // TODO: 2021/07/21 로딩 처리
            }
        }
    }
}

