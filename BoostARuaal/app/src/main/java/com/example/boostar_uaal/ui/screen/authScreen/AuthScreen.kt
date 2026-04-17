package com.example.boostar_uaal.ui.screen.authScreen

import androidx.compose.runtime.Composable
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthLayout

@Composable
fun AuthScreen(
    navigateTo: (Routes, Boolean) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit
) {
    AuthLayout(
        title = "Boostar",
        subtitle = "Try it first.",
        onBackClick = null
    ) {
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
