package com.example.boostar_uaal.ui.screen.authScreen.components

import android.util.Log
import androidx.compose.runtime.Composable
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.NativeSignInState
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle


@Composable
fun GoogleAuthButton(composeAuth: ComposeAuth, onResult: (NativeSignInResult) -> Unit){
    val authState = composeAuth.rememberSignInWithGoogle(onResult)

    AuthButton(
        onClick = {
            authState.startFlow()
            Log.d("Auth", "$authState")
                  },
        text = "Continue with Google",
        isFilled = true,
    )
}