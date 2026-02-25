package com.example.boostar_uaal.ui.screen.loginScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.BoostArApplication.Companion.composeAuth
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.AuthLayout
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.theme.secondaryButtonColor
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.components.GoogleAuthButton
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult

@Composable
fun LogInScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
    viewModel: LoginScreenViewModel = viewModel()
) {
    // Usamos nuestra carcasa maestra
    AuthLayout(
        imageRes = R.drawable.carrusel_auth_2,
        title = "BoostAR",
        subtitle = "Log in the future.",
        onBackClick = { back() }
    ) {

        GoogleAuthButton(
            composeAuth = composeAuth, // conexion supabase
            onResult = { result ->
                viewModel.handleGoogleSignInResult(result, navigateTo)
            }
        )
        AuthButton(
            onClick = { navigateTo(Routes.SignInScreen) },
            text = "Continue with phone",
            icon = R.drawable.phone_icon,
            isFilled = true
        )

        AuthButton(
            onClick = { navigateTo(Routes.HomeScreen) },
            text = "Continue with apple",
            isFilled = false
        )
    }
}




