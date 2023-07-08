package com.halilkrkn.cryptocurrency.presentation.coin_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.halilkrkn.cryptocurrency.R
import com.halilkrkn.cryptocurrency.presentation.animation.CoinLottieAnimation
import com.halilkrkn.cryptocurrency.presentation.coin_detail.components.CoinTag
import com.halilkrkn.cryptocurrency.presentation.coin_detail.components.TeamListItem

@SuppressLint("StateFlowValueCalledInComposition", "RememberReturnType")
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val isRefreshing = viewModel.isLoading.value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onRefresh(coin.coinId) }
            ) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White,
                                modifier = Modifier.weight(8f)
                            )
                            Text(
                                text = if (coin.isActive) "active" else "inactive",
                                color = if (coin.isActive) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .weight(2f)
                            )

                        }
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(
                            text = coin.description,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(
                            text = "Tags",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Spacer(modifier = Modifier.padding(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            coin.tags.forEach { tag ->
                                CoinTag(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(
                            text = "Team members",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Spacer(modifier = Modifier.padding(15.dp))
                    }
                    items(coin.team) { teamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()
                    }
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CoinLottieAnimation(
                modifier = Modifier.align(Alignment.Center),
                raw = R.raw.lottie_comp
            )
        }
    }
}