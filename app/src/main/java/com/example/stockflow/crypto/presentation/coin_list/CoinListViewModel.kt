package com.example.stockflow.crypto.presentation.coin_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockflow.core.domain.util.onError
import com.example.stockflow.core.domain.util.onSuccess
import com.example.stockflow.crypto.domain.CoinDataSource
import com.example.stockflow.crypto.presentation.models.CoinUI
import com.example.stockflow.crypto.presentation.models.toCoinUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel
    (private val coinDataSource: CoinDataSource) : ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), CoinListState()
    )


    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()


    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                selectCoin(action.coinUI)
            }

            CoinListAction.OnRefresh -> loadCoins()
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            coinDataSource.getCoins().onSuccess { coins ->
                _state.update { it ->
                    it.copy(
                        isLoading = false,
                        coins = coins.map { coin -> coin.toCoinUI() },
                    )
                }

            }.onError { error ->
                _state.update {
                    it.copy(isLoading = false)
                }
                _events.send(CoinListEvent.Error(error))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectCoin(coinUI: CoinUI) {
        _state.update {
            it.copy(selectedCoin = coinUI)
        }

        viewModelScope.launch {
            coinDataSource.getCoinHistory(
                coinId = coinUI.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now()
            ).onSuccess { history ->


            }.onError { error ->
                _events.send(CoinListEvent.Error(error))
            }
        }

    }


}