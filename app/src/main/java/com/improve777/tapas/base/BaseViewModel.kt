package com.improve777.tapas.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.improve777.tapas.domain.model.State

open class BaseViewModel : ViewModel() {

    protected val _error = MutableLiveData<State.Error>()
    val error: LiveData<State.Error> = _error

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
}