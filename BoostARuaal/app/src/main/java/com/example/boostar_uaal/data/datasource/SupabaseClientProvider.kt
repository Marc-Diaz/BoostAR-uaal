package com.example.boostar_uaal.data.datasource

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
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
                install(Postgrest)
                install(Storage)

            }
        )
    }
}