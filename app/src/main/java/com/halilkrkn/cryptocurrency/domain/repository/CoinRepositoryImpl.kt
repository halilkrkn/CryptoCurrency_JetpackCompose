package com.halilkrkn.cryptocurrency.domain.repository

import com.halilkrkn.cryptocurrency.data.remote.api.CoinPaprikaApi
import com.halilkrkn.cryptocurrency.data.remote.dto.CoinDetailDto
import com.halilkrkn.cryptocurrency.data.remote.dto.CoinDto
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository  {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }


}