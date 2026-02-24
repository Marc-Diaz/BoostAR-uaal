package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.core.entities.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.rpc


class ProductRepositoryImpl(private val postgrest: Postgrest): ProductRepository  {
    override suspend fun getProducts(): List<Product> {
        val response: List<Product> = postgrest["Producto_View"].select().decodeList<Product>()

        return response
    }

    override suspend fun getProductById(id: Int): ProductDetail {
        val response: ProductDetail = postgrest.rpc(
            function = "get_product_detail",
            parameters = mapOf("p_product_id" to id)
        ).decodeSingle()

        return response
    }
}