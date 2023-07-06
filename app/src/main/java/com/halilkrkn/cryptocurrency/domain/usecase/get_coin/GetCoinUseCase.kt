package com.halilkrkn.cryptocurrency.domain.usecase.get_coin

import com.halilkrkn.cryptocurrency.common.Resource
import com.halilkrkn.cryptocurrency.data.remote.mappers.toCoin
import com.halilkrkn.cryptocurrency.data.remote.mappers.toCoinDetail
import com.halilkrkn.cryptocurrency.domain.model.Coin
import com.halilkrkn.cryptocurrency.domain.model.CoinDetail
import com.halilkrkn.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        }
        catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}