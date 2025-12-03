package com.example.stockflow.crypto.presentation.coin_list

import com.example.stockflow.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}
