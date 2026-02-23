package com.example.boostar_uaal.data.datasource

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

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