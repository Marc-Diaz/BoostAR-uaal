package com.example.boostar_uaal.data.datasource

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
/**
 * Proveedor principal que inicializa y expone la conexión central con Supabase.
 *
 * Configura el cliente instanciando los módulos estrictamente necesarios para la app:
 * - Auth y ComposeAuth: Gestión de sesiones de usuario y login nativo con Google.
 * - Postgrest: Interacción con las tablas de la base de datos.
 * - Storage: Alojamiento y recuperación de archivos (imágenes, vídeos).
 *
 * @param supabaseUrl URL base del servidor del proyecto en Supabase.
 * @param supabaseKey Clave pública (anon key) para acceder a la API.
 */
class SupabaseClientProvider {
    var client: SupabaseClient
        private set

    constructor(supabaseUrl: String, supabaseKey: String) {

        client = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey,
            builder = {
                install(Auth)
                install(ComposeAuth) {
                    googleNativeLogin("434541873190-n6jfs5gmv9h3984pmpmslp5p6m0t7l2g.apps.googleusercontent.com")
                }
                install(Postgrest)
                install(Storage)

            }
        )
    }
}