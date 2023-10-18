package com.mirkwoodsoftware.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.mirkwoodsoftware.presentation.NetworkStatusViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BaseScreen(content: @Composable () -> Unit, networkStatusViewModel: NetworkStatusViewModel = koinViewModel()) {
    MaterialTheme {

    }
}
