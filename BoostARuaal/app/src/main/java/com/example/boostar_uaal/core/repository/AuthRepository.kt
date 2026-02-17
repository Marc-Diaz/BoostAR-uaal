package com.example.boostar_uaal.core.repository

import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun signInWithGoogle(onResult: (NativeSignInResult) -> Unit)

}