package com.example.boostar_uaal.ui.screen.authScreen.components

import androidx.compose.runtime.Composable
import io.github.jan.supabase.compose.auth.composable.NativeSignInState


@Composable
fun GoogleAuthButton(authState: NativeSignInState){

    AuthButton(
        onClick = { authState.startFlow() },
        text = "Continue with Google",
        isFilled = true,
    )
}