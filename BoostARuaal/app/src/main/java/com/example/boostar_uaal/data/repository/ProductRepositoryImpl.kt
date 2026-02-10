package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.core.entities.Product

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class ProductRepositoryImpl(private val supabaseClient: SupabaseClient): ProductRepository {

    override suspend fun getProducts(): List<Product> {
        val products = supabaseClient.postgrest["Productos_View"].select().decodeList<Product>()
        return products
    }
}