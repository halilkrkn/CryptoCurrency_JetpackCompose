package com.halilkrkn.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.halilkrkn.cryptocurrency.common.Resource
import com.halilkrkn.cryptocurrency.domain.usecase.get_coins.GetCoinsUseCase
import com.halilkrkn.cryptocurrency.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : BaseViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    fun onRefresh() {
        getCoins()
    }

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        hideLoading()
                        _state.value = CoinListState(coins = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        hideLoading()
                        _state.value =
                            CoinListState(error = result.message ?: "An unexpected error occurred")
                    }

                    is Resource.Loading -> {
                        showLoading()
                        _state.value = CoinListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}