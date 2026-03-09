package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.entities.User
import com.example.boostar_uaal.core.repository.UserRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.http.parameters
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

class UserRepositoryImpl(private val auth: Auth, private val postgrest: Postgrest): UserRepository {
    override suspend fun getUserProfile(): User {
        val response = postgrest.rpc("get_user_profile").decodeSingle<User>()
        return response
    }

    override suspend fun setUserRole(isCompany: Boolean){

        val metadata = buildJsonObject {
            put( "isCompany", isCompany)
        }
        auth.updateUser {
            data = metadata
        }
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

    override suspend fun updateOnboardingPreferences(preferences: Map<String, List<Int>>) {

        val json = Json.encodeToJsonElement(preferences)
        postgrest.rpc(
            function = "update_onboarding",
            parameters = buildJsonObject { put("p_onboarding_preferences", json) }
        )
    }
}