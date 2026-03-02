package com.example.boostar_uaal.data.repository


import android.util.Log
import com.example.boostar_uaal.core.repository.AuthRepository

import io.github.jan.supabase.auth.Auth



class AuthRepositoryImpl(private val auth: Auth): AuthRepository {
    override suspend fun saveSession(){
        val userSession = auth.currentSessionOrNull() ?: return
        Log.d("Session", "Guardada")
        auth.sessionManager.saveSession(userSession)
    }

    override suspend fun loadSession() = auth.sessionManager.loadSession()

    override suspend fun clearSession(){
        auth.sessionManager.deleteSession()
    }


}