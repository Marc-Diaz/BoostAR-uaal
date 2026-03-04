package com.example.boostar_uaal.data.repository


import android.util.Log
import com.example.boostar_uaal.core.repository.AuthRepository

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.user.UserUpdateBuilder
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlin.collections.mapOf


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

    override suspend fun setUserRole(isCompany: Boolean){

        val metadata = buildJsonObject {
            put( "isCompany", isCompany)
        }
        auth.updateUser {
            data = metadata
        }
        Log.d("AuthUpdate", "DONE")
    }

    override suspend fun hasUserRole(): Boolean {
        val userSession = auth.currentSessionOrNull()
        val metadata = userSession?.user?.userMetadata

        return metadata?.containsKey("isCompany") ?: false

    }

    override suspend fun isCompanyUser(): Boolean {
        val userSession = auth.currentSessionOrNull()
        val metadata = userSession?.user?.userMetadata

        return metadata?.get("isCompany")?.jsonPrimitive?.booleanOrNull ?: false
    }
}