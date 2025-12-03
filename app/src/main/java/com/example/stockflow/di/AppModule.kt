package com.example.stockflow.di

import com.example.stockflow.core.data.networking.HttpClientFactory
import com.example.stockflow.crypto.data.networking.RemoteCoinDataSource
import com.example.stockflow.crypto.domain.CoinDataSource
import com.example.stockflow.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create(CIO.create()) }
    single<CoinDataSource> { RemoteCoinDataSource(get()) }
    viewModelOf(::CoinListViewModel)
}
