package com.example.stockflow.crypto.presentation.coin_list

import com.example.stockflow.crypto.presentation.models.CoinUI

sealed interface CoinListAction {
    data class OnCoinClick(val coinUI: CoinUI) : CoinListAction
    data object OnRefresh: CoinListAction
}