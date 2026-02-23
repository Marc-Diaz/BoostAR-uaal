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
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.secondaryButtonColor
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.data.datasource.SharedPreferencesHelper
import com.example.boostar_uaal.ui.screen.authScreen.components.GoogleAuthButton
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
fun SignInScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.carrusel_auth_2),
            contentDescription = "Auth Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 0.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        )
        {
            Column(
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 24.dp
                )
            )
            {
                InterText(
                    "BoostAR.",
                    color = secondaryButtonColor,
                    fontSize = 54.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                InterText(
                    "Try it first.",
                    color = secondaryButtonColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth().height(295.dp),
                color = secondaryButtonColor,
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
            )
            {
                Column(
                    modifier = Modifier.fillMaxSize().padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    GoogleAuthButton(
                        composeAuth = composeAuth,
                        onResult = {
                            when(it){
                                is NativeSignInResult.Success -> navigateTo(Routes.Authenticated)
                                is NativeSignInResult.Error -> navigateTo(Routes.AuthScreen)
                                else -> {
                                    Log.d("ERROR", "$it")
                                }
                            }
                        }
                    )
                    AuthButton(
                        onClick = { navigateTo(Routes.SignInScreen) },
                        text = "Continue with phone",
                        isFilled = true,
                    )
                }
            }
        }
    }
}