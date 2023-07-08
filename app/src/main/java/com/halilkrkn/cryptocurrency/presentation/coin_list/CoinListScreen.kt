package com.halilkrkn.cryptocurrency.presentation.coin_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.halilkrkn.cryptocurrency.R
import com.halilkrkn.cryptocurrency.presentation.Screen
import com.halilkrkn.cryptocurrency.presentation.animation.lottie.CoinLottieAnimation
import com.halilkrkn.cryptocurrency.presentation.animation.shimmer.ShimmerEffect
import com.halilkrkn.cryptocurrency.presentation.coin_list.components.CoinListItem


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val isRefreshing = viewModel.isLoading.value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)



    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = viewModel::onRefresh
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    CoinListItem(
                        coin = coin, onItemClick = {
                            navController.navigate(
                                Screen.CoinDetailScreen.route + "/${coin.id}"
                            )
                        }
                    )
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
            ShimmerEffect()
//            CoinLottieAnimation(
//                modifier = Modifier
//                    .size(250.dp, 250.dp)
//                    .align(Alignment.Center),
//                raw = R.raw.lottie_crypto
//            )
        }
    }
}
