package com.example.boostar_uaal.ui.screen.loginScreen

import android.widget.Toast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.BoostArApplication.Companion.composeAuth
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.AuthLayout
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.components.GoogleAuthButton
@Composable
fun LogInScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit
) {
    val context = LocalContext.current
    val viewModel = viewModel<LoginScreenViewModel>()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }
    AuthLayout(
        imageRes = R.drawable.carrusel_auth_2,
        title = "BoostAR",
        subtitle = "Inicia sesión en el futuro.",
        onBackClick = { back() }
    ) {

        GoogleAuthButton(
            composeAuth = composeAuth,
            onResult = { result ->
                viewModel.handleGoogleSignInResult(result, navigateTo)
            }
        )
        AuthButton(
            onClick = { navigateTo(Routes.SignInScreen) },
            text = "Continuar con teléfono.",
            icon = R.drawable.phone_logo,
            isFilled = true
        )

        AuthButton(
            onClick = { navigateTo(Routes.HomeScreen) },
            text = "Continuar con apple",
            isFilled = false
        )
    }
}




