package com.base.pokedex.ui.screen.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Space(
    height: Dp = 15.dp,
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .height(height)
    )
}

@Composable
fun LoadingIndicator(
    width: Dp = 10.dp,
    size: Dp = 100.dp
) {
    CircularProgressIndicator(
        strokeWidth = width,
        modifier = Modifier
            .size(size)
    )
}
