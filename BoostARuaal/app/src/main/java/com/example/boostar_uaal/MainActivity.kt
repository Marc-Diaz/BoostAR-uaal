package com.example.boostar_uaal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.theme.BoostARuaalTheme
import com.example.boostar_uaal.core.navigation.MainNavigationWrapper
import com.example.boostar_uaal.core.utils.AuthState
import com.example.boostar_uaal.ui.screen.authScreen.AuthScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        setContent {
            val authViewModel = viewModel<AuthScreenViewModel>()
            val authState by authViewModel.authState.collectAsState()

            splashScreen.setKeepOnScreenCondition {
                authState == AuthState.Loading
            }
            if (authState != AuthState.Loading){
                BoostARuaalTheme {
                    MainNavigationWrapper(authState)
                }
            }
        }
    }
}

