package com.halilkrkn.cryptocurrency.presentation.coin_detail

import com.halilkrkn.cryptocurrency.domain.model.Coin
import com.halilkrkn.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    var isLoading: Boolean = false,
    val coin: CoinDetail ?= null,
    val error: String = ""
)
