package com.example.stockflow.crypto.data.mappers

import com.example.stockflow.crypto.data.networking.dto.CoinDto
import com.example.stockflow.crypto.data.networking.dto.CoinPriceDto
import com.example.stockflow.crypto.domain.Coin
import com.example.stockflow.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        changePercent24Hr = changePercent24Hr,
        priceUsd = priceUsd
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.of("UTC"))
    )
}