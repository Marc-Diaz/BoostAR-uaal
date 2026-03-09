package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.User
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun saveSession()
    suspend fun clearSession()
    suspend fun loadSession(): UserSession?
    suspend fun isAccessTokenValid(): Boolean
    suspend fun refreshSession(): Boolean
}