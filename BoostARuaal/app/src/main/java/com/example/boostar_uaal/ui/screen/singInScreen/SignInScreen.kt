package com.example.boostar_uaal.ui.screen.singInScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.BoostArApplication.Companion.composeAuth
import com.example.boostar_uaal.R
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthLayout
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.AuthViewModel
import com.example.boostar_uaal.ui.screen.authScreen.components.GoogleAuthButton
import com.example.boostar_uaal.ui.screen.singInScreen.components.CompanyAccountSelector
@Composable
fun SignInScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
) {
    val context = LocalContext.current
    val viewModel = viewModel<AuthViewModel>()
    val isCompanyAccount by viewModel.isCompanyAccount.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }
    AuthLayout(
        title = "Boostar",
        subtitle = "Regístrate en el futuro.",
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
            text = "Continuar con teléfono",
            icon = R.drawable.phone_logo,
            isFilled = true,
        )


        CompanyAccountSelector(
            isChecked = isCompanyAccount,
            onToggle = { viewModel.toggleCompanyAccount() }
        )
    }
}