package com.mirkwoodsoftware.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mirkwoodsoftware.presentation.navigation.HomeApp
import com.mirkwoodsoftware.presentation.navigation.NavigationHost

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeApp()
        }
    }
}
