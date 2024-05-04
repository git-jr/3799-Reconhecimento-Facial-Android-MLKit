package com.alura.sorria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alura.sorria.ui.screens.home.HomeNavHost
import com.alura.sorria.ui.theme.SorriaTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SorriaTheme {
                HomeNavHost()
            }
        }
    }
}


