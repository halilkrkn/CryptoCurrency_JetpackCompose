package com.halilkrkn.cryptocurrency.presentation.animation.lottie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun CoinLottieAnimation(
    modifier: Modifier = Modifier,
    raw: Int
) {
    Box(
        modifier = modifier
            .size(75.dp,75.dp),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
            speed = 2f,
            alignment = Alignment.Center,
        )
    }
}