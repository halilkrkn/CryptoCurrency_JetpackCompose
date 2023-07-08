package com.halilkrkn.cryptocurrency.presentation.animation.shimmer

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ShimmerEffect() {
    LazyColumn(Modifier.fillMaxSize()) {
        items(15) { coin ->
            ShimmerEffectItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
    }

}


@Composable
fun LoadingShimmer() {
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        delay(5000)
        isLoading = false
    }

    LazyColumn(Modifier.fillMaxSize()) {
        items(10) { coin ->
            LoadingShimmerItem(
                isLoading = isLoading,
                contentAfterLoading = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

