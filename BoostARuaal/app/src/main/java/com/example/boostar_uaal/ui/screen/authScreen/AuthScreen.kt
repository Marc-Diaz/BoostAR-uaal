package com.example.boostar_uaal.ui.screen.authScreen

import androidx.compose.runtime.Composable
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AuthLayout

@Composable
fun AuthScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
    viewModel: AuthScreenViewModel = viewModel()
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
