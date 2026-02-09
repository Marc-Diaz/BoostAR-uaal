package com.example.boostar_uaal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.boostar_uaal.core.theme.BoostARuaalTheme
import com.example.boostar_uaal.core.navigation.MainNavigationWrapper

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            BoostARuaalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainNavigationWrapper()
                }
            }
        }
    }
}

