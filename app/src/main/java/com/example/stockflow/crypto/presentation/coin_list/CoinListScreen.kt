package com.example.stockflow.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.stockflow.core.presentation.util.toString
import com.example.stockflow.crypto.presentation.coin_list.components.CoinListItem
import com.example.stockflow.crypto.presentation.coin_list.components.previewCoin
import com.example.stockflow.crypto.presentation.models.toCoinUI
import com.example.stockflow.ui.theme.StockFlowTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    state: CoinListState,
    action: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {

    if (state.isLoading) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUI ->
                CoinListItem(
                    coinUI, onClick = {
                        action(CoinListAction.OnCoinClick(coinUI))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@PreviewLightDark
@Composable
private fun CoinListPrev() {
    StockFlowTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..100).map {
                    previewCoin.toCoinUI().copy(id = it.toString())
                }
            ),
            action = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}


/*
*   Data->Domain->Presentation
*       Presentation -> UI + State(Without User Interaction) + Events + Model   = Screen
*       Domain -> Model + Definition of the data source functionality (Interface or Blueprint)
* */