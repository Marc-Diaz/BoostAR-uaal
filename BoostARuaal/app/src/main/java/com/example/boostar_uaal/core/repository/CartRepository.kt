package com.example.boostar_uaal.core.repository

interface CartRepository {
    suspend fun addProductToCart(productId: Int, colorId: Int, clothingSizeId: Int, quantity: Int = 1)
    suspend fun increaseProductQuantity(variantId: Int): Int
    suspend fun decreaseProductQuantity(variantId: Int): Int

}