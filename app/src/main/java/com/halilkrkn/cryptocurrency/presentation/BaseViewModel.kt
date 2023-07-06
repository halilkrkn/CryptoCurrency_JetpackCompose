package com.halilkrkn.cryptocurrency.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val error: MutableLiveData<String> = MutableLiveData()

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

    fun showError(errorMessage: String) {
        error.value = errorMessage
    }
}

