package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AuthRepositoryImpl(private val supabaseClient: SupabaseClient): AuthRepository {



    override suspend fun signInWithGoogle(onResult: (NativeSignInResult) -> Unit) {

    }



}