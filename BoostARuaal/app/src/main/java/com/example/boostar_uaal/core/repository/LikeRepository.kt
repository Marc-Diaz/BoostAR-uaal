package com.example.boostar_uaal.core.repository

import com.example.core.entities.Product
interface LikeRepository {
    suspend fun toggleLike(id: Int): Boolean
    suspend fun getLikeList(): List<Product>
}