package com.halilkrkn.cryptocurrency.domain.repository

import com.halilkrkn.cryptocurrency.data.remote.dto.CoinDetailDto
import com.halilkrkn.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}