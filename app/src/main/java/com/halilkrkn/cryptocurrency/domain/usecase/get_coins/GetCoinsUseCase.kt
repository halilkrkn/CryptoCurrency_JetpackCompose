package com.halilkrkn.cryptocurrency.domain.usecase.get_coins

import com.halilkrkn.cryptocurrency.common.Resource
import com.halilkrkn.cryptocurrency.data.remote.mappers.toCoin
import com.halilkrkn.cryptocurrency.domain.model.Coin
import com.halilkrkn.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        }
        catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}