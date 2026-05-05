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
/**
 * Implementación del repositorio que gestiona el perfil, los roles y las
 * preferencias del usuario interactuando con Supabase (Auth y Postgrest).
 */
class UserRepositoryImpl(private val auth: Auth, private val postgrest: Postgrest): UserRepository {

    /**
     * Recupera el perfil completo del usuario actual desde la base de datos.
     *
     * @return Objeto `User` con la información del perfil.
     */
    override suspend fun getUserProfile(): User {
        val response = postgrest.rpc("get_user_profile").decodeSingle<User>()
        return response
    }
    /**
     * Asigna o actualiza el rol del usuario guardándolo en los metadatos de su sesión.
     *
     * @param isCompany `true` si la cuenta pertenece a una empresa, `false` si es un usuario estándar.
     */
    override suspend fun setUserRole(isCompany: Boolean){

        val metadata = buildJsonObject {
            put( "isCompany", isCompany)
        }
        auth.updateUser {
            data = metadata
        }
    }
    /**
     * Verifica si el usuario ya ha definido un rol previamente.
     *
     * @return `true` si el metadato de rol existe en la sesión actual, `false` si aún no se ha configurado.
     */
    override suspend fun hasUserRole(): Boolean {
        val userSession = auth.currentSessionOrNull()
        val metadata = userSession?.user?.userMetadata

        return metadata?.containsKey("isCompany") ?: false

    }
    /**
     * Comprueba activamente si el usuario actual opera bajo el rol de empresa.
     *
     * @return `true` si es empresa, `false` en cualquier otro caso.
     */
    override suspend fun isCompanyUser(): Boolean {
        val userSession = auth.currentSessionOrNull()
        val metadata = userSession?.user?.userMetadata

        return metadata?.get("isCompany")?.jsonPrimitive?.booleanOrNull ?: false
    }
    /**
     * Guarda las selecciones del usuario realizadas durante el flujo de configuración inicial.
     *
     * @param preferences Mapa que asocia una categoría (ej. "tops", "bottoms") con una lista de IDs de estilos elegidos.
     */

    override suspend fun updateOnboardingPreferences(preferences: Map<String, List<Int>>) {

        val json = Json.encodeToJsonElement(preferences)
        postgrest.rpc(
            function = "update_onboarding",
            parameters = buildJsonObject { put("p_onboarding_preferences", json) }
        )
    }
}