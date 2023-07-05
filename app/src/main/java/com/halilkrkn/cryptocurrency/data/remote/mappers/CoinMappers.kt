package com.halilkrkn.cryptocurrency.data.remote.mappers

import com.halilkrkn.cryptocurrency.data.remote.dto.CoinDetailDto
import com.halilkrkn.cryptocurrency.data.remote.dto.CoinDto
import com.halilkrkn.cryptocurrency.domain.model.Coin
import com.halilkrkn.cryptocurrency.domain.model.CoinDetail


// Burada ne yapılıyor?
// CoinDto sınıfından bir nesne alıyoruz ve bu nesneyi Coin sınıfına dönüştürüyoruz.
// Bu dönüşümü yaparken de CoinDto sınıfının içindeki verileri Coin sınıfının içindeki verilere atıyoruz.
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

// Burada ne yapılıyor?
// CoinDetailDto sınıfından bir nesne alıyoruz ve bu nesneyi CoinDetail sınıfına dönüştürüyoruz.
// Bu dönüşümü yaparken de CoinDetailDto sınıfının içindeki verileri CoinDetail sınıfının içindeki verilere atıyoruz.
// Ayrıca CoinDetailDto sınıfının içindeki tags ve team verilerini CoinDetail sınıfının içindeki tags ve team verilerine atıyoruz.
// Burada tags ve team verilerini atarken de CoinDetailDto sınıfının içindeki tags ve team verilerini CoinDetail sınıfının içindeki tags ve team verilerine dönüştürüyoruz.
// Bu dönüşümü yaparken de CoinDetailDto sınıfının içindeki tags ve team verilerini CoinDetail sınıfının içindeki tags ve team verilerine atıyoruz.
fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        rank = rank,
        symbol = symbol,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team
    )
}
