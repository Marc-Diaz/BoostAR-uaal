package com.example.boostar_uaal.data.repository


import com.example.boostar_uaal.core.repository.AuthRepository
import io.github.jan.supabase.auth.Auth
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


class AuthRepositoryImpl(private val auth: Auth): AuthRepository {

    override suspend fun saveSession(){
        val userSession = auth.currentSessionOrNull() ?: return
        auth.sessionManager.saveSession(userSession)
    }

    override suspend fun loadSession() = auth.sessionManager.loadSession()

    override suspend fun clearSession(){
        auth.sessionManager.deleteSession()

    }


    @OptIn(ExperimentalTime::class)
    override suspend fun isAccessTokenValid(): Boolean {
        val session = auth.currentSessionOrNull() ?: return false
        val now = Instant.fromEpochMilliseconds(System.currentTimeMillis())
        return session.expiresAt > now
    }

    override suspend fun refreshSession(): Boolean{
        try {
            auth.refreshCurrentSession()
            return true
        }
        catch (e: Exception){
            clearSession()
            return false
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}