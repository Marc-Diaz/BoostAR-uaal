package com.example.boostar_uaal.ui.screen.authScreen.components

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.boostar_uaal.R
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.NativeSignInState
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle


@Composable
fun GoogleAuthButton(composeAuth: ComposeAuth, onResult: (NativeSignInResult) -> Unit){
    val authState = composeAuth.rememberSignInWithGoogle(onResult = onResult)
    val icon = R.drawable.google_logo
    AuthButton(
        onClick = { authState.startFlow() },
        text = "Continuar con Google",
        isFilled = true,
        icon = icon
    )
}
