package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.core.entities.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): ProductDetail
}