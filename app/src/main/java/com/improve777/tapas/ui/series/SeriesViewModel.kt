package com.improve777.tapas.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.improve777.tapas.base.BaseViewModel
import com.improve777.tapas.domain.model.SeriesInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor() : BaseViewModel() {

    private val _seriesInfo = MutableLiveData<SeriesInfo>()
    val seriesInfo: LiveData<SeriesInfo> = _seriesInfo

    fun setData(seriesInfo: SeriesInfo) {
        _seriesInfo.value = seriesInfo
    }
}