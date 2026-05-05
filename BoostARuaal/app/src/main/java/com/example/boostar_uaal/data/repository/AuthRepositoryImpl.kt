package com.example.boostar_uaal.data.repository


import com.example.boostar_uaal.core.repository.AuthRepository
import io.github.jan.supabase.auth.Auth
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


class AuthRepositoryImpl(private val auth: Auth): AuthRepository {
    /**
     * Guarda localmente la sesión actual del usuario.
     *
     * Si existe una sesión activa, la persiste utilizando el gestor de sesiones.
     * Si no hay ninguna sesión válida en curso, la función se interrumpe de inmediato
     * sin realizar ninguna acción.
     */
    override suspend fun saveSession(){
        val userSession = auth.currentSessionOrNull() ?: return
        auth.sessionManager.saveSession(userSession)
    }

    override suspend fun loadSession() = auth.sessionManager.loadSession()

    override suspend fun clearSession(){
        auth.sessionManager.deleteSession()

    }

    /**
     * Verifica si el token de acceso de la sesión actual sigue siendo válido.
     *
     * Comprueba la existencia de una sesión activa y compara su fecha de caducidad
     * (`expiresAt`) con la hora exacta actual del sistema.
     *
     * @return `true` si hay una sesión activa y su token no ha expirado; `false` en caso contrario.
     */
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