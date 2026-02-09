package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.data.models.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class ProductRepositoryImpl(private val supabaseClient: SupabaseClient) {

    suspend fun getProducts(){
        val products = supabaseClient.postgrest["Productos_View"].select().decodeList<Product>()
    }
}