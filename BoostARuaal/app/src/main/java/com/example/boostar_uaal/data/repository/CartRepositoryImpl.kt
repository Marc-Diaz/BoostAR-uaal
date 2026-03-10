package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.repository.CartRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class CartRepositoryImpl(private val postgrest: Postgrest): CartRepository {
    override suspend fun addProductToCart(
        productId: Int,
        colorId: Int,
        clothingSizeId: Int,
        quantity: Int
    ) {
        postgrest.rpc(
            function = "insert_product_into_cart",
            parameters = buildJsonObject {
                put("p_product_id", productId)
                put("p_color_id", colorId)
                put("p_size_id", clothingSizeId)
                put("p_quantity_id", quantity)

            }
        )
    }

    override suspend fun increaseProductQuantity(variantId: Int): Int {
        val response = postgrest.rpc(
            function = "increase_variant_quantity",
            parameters = buildJsonObject {
                put("p_variant_id", variantId)
            }).decodeAs<Int>()
        return response
    }

    override suspend fun decreaseProductQuantity(variantId: Int): Int {
        val response = postgrest.rpc(
            function = "decrease_variant_quantity",
            parameters = buildJsonObject {
                put("p_variant_id", variantId)
            }).decodeAs<Int>()
        return response
    }
}