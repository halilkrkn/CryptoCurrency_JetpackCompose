package com.halilkrkn.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.halilkrkn.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.halilkrkn.cryptocurrency.common.Resource
import com.halilkrkn.cryptocurrency.domain.usecase.get_coin.GetCoinUseCase
import com.halilkrkn.cryptocurrency.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

//    private val _isLoading = MutableStateFlow(false)
//    val isLoading = _isLoading.asStateFlow()

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    fun onRefresh(coinId: String) {
        getCoin(coinId = coinId)
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            getCoinUseCase(coinId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        hideLoading()
                        _state.value = CoinDetailState(coin = result.data)
                    }

                    is Resource.Error -> {
                        hideLoading()
                        _state.value = CoinDetailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        showLoading()
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
//        _isLoading.value = false
        _state.value.isLoading = false
    }
}