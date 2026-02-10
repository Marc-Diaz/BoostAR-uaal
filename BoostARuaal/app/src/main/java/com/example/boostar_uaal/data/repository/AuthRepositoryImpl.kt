package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.repository.AuthRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google


class AuthRepositoryImpl(private val auth: Auth): AuthRepository {

    override suspend fun signInWithGoogle(){
        auth.signInWith(Google)
    }


}