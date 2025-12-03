package com.example.stockflow.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockflow.crypto.domain.Coin
import com.example.stockflow.crypto.presentation.models.CoinUI
import com.example.stockflow.crypto.presentation.models.toCoinUI
import com.example.stockflow.ui.theme.StockFlowTheme

@Composable
fun CoinListItem(
    coinUI: CoinUI,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    return Row(
        modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(coinUI.iconRes),
            contentDescription = coinUI.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )
        Column(modifier.weight(1f)) {
            Text(
                coinUI.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Text(
                coinUI.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )

        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                "$ ${coinUI.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
            Spacer(modifier.height(8.dp))
            PriceChange(coinUI.changePercent24Hr)

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@PreviewLightDark
@Composable
fun CoinListItemPre() {
    StockFlowTheme {
        CoinListItem(
            previewCoin.toCoinUI(),
            onClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

internal val previewCoin = Coin(
    id = "bitcoin",
    name = "Bitcoin",
    symbol = "BTC",
    rank = 1,
    priceUsd = 624545.45,
    marketCapUsd = 12312341241.56,
    changePercent24Hr = 0.1,
)