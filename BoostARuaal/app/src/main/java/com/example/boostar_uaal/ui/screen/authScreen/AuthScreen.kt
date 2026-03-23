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
    navigateTo: (Routes, Boolean) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit
) {
    // Llamamos a nuestra "carcasa" y le pasamos solo los datos visuales
    AuthLayout(
        imageRes = R.drawable.carrusel_auth_2,
        title = "Boostar",
        subtitle = "Try it first.",
        onBackClick = null // Como es la primera pantalla, no ponemos botón de atrás
    ) {
        // Y aquí rellenamos el "hueco" con nuestros botones específicos
        AuthButton(
            onClick = { navigateTo(Routes.LogInScreen, false) },
            text = "Iniciar sesión",
            isFilled = true
        )

        AuthButton(
            onClick = { navigateTo(Routes.SignInScreen, false) },
            text = "Registrarse",
            isFilled = true
        )

        AuthButton(
            onClick = { navigateTo(Routes.HomeScreen, true) },
            text = "Entrar como invitado",
            isFilled = false
        )
    }
}
