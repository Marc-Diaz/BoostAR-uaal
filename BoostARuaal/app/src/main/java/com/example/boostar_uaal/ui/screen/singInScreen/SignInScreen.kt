package com.example.boostar_uaal.ui.screen.singInScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.BoostArApplication.Companion.composeAuth
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.AuthLayout
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.secondaryButtonColor
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.data.datasource.SharedPreferencesHelper
import com.example.boostar_uaal.ui.screen.authScreen.components.GoogleAuthButton
import com.example.boostar_uaal.ui.screen.singInScreen.components.CompanyAccountSelector
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.ktor.utils.io.pool.SingleInstancePool

@Composable
fun SignInScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
) {
    val context = LocalContext.current
    val viewModel = viewModel<SingUpScreenViewModel>(
        factory = SingUpScreenViewModelFactory(
            BoostArApplication.authRepository, SharedPreferencesHelper(context)
        )
    )
    val isCompanyAccount by viewModel.isCompanyAccount.collectAsState()

    AuthLayout(
        imageRes = R.drawable.carrusel_auth_2, // Tu imagen del chico con gafas
        title = "Boostar",
        subtitle = "Sign in in the future.",
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
            text = "Continue with phone",
            isFilled = true,
        )


        CompanyAccountSelector(
            isChecked = isCompanyAccount,
            onToggle = { viewModel.toggleCompanyAccount() }
        )
    }
}