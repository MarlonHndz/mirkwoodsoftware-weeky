package com.mirkwoodsoftware.presentation.ui.networkHandler

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun BaseScreen(content: @Composable () -> Unit, networkStatusViewModel: NetworkStatusViewModel = koinViewModel()) {
    MaterialTheme {

    }
}
