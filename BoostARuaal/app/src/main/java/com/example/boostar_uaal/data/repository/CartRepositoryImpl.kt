package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.repository.CartRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class CartRepositoryImpl(private val postgrest: Postgrest): CartRepository {
    override suspend fun addProductToCart(
        productId: Int,
        colorId: Int?,
        clothingSizeId: Int,
        quantity: Int
    ) {
        try {
            postgrest.rpc(
                function = "insert_product_into_cart",
                parameters = buildJsonObject {
                    put("p_product_id", productId)
                    if (colorId != null) put("p_color_id", colorId) else put("p_color_id", JsonNull)
                    put("p_size_id", clothingSizeId)
                    put("p_quantity", quantity)

                }
            )
        }
        catch (e: Exception){
            if (e.message?.contains("duplicate key value violates unique constraint \"Detalle_Cesta_pkey\"") ?: false )
                Log.d("CartRepository", "El producto ya se encuentra en la cesta")
        }
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