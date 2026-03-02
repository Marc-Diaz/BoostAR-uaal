package com.example.boostar_uaal.ui.screen.authScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.components.AuthLayout
import com.example.boostar_uaal.ui.screen.homeScreen.HomeScreenViewModel

@Composable
fun AuthScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit
) {
    val authScreenViewModel = viewModel<AuthScreenViewModel>()
    val authenticated by authScreenViewModel.authenticated.collectAsState()
    LaunchedEffect(Unit) {
        Log.d("AuthScreenViewModel", "$authenticated")
        if(authenticated) navigateTo(Routes.HomeScreen)
    }
    // Llamamos a nuestra "carcasa" y le pasamos solo los datos visuales
    AuthLayout(
        imageRes = R.drawable.carrusel_auth_2,
        title = "Boostar",
        subtitle = "Try it first.",
        onBackClick = null // Como es la primera pantalla, no ponemos botón de atrás
    ) {
        // Y aquí rellenamos el "hueco" con nuestros botones específicos
        AuthButton(
            onClick = { navigateTo(Routes.LogInScreen) },
            text = "Log in",
            isFilled = true
        )

        AuthButton(
            onClick = { navigateTo(Routes.SignInScreen) },
            text = "Sign up",
            isFilled = true
        )

        AuthButton(
            onClick = { navigateTo(Routes.HomeScreen) },
            text = "Enter as guest",
            isFilled = false
        )
    }
}
